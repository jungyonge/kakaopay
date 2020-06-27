package com.jungyonge.kakaopay.service;

import com.jungyonge.kakaopay.exception.ShareEventException;
import com.jungyonge.kakaopay.model.*;
import com.jungyonge.kakaopay.repository.RoomRepository;
import com.jungyonge.kakaopay.repository.ShareEventDetailRepository;
import com.jungyonge.kakaopay.repository.ShareEventRepository;
import com.jungyonge.kakaopay.repository.UserRepository;
import com.jungyonge.kakaopay.util.RandomTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class ShareEventService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final ShareEventRepository shareEventRepository;

    private final ShareEventDetailRepository shareEventDetailRepository;

    public ShareEventService(UserRepository userRepository, RoomRepository roomRepository, ShareEventRepository shareEventRepository, ShareEventDetailRepository shareEventDetailRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.shareEventRepository = shareEventRepository;
        this.shareEventDetailRepository = shareEventDetailRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addShareEvent(int xUserId, int xRoomId, int totalShareMoney, int totalSharePeople) {
        int hostTotalMoney = 0;
        Map<String, Object> resultMap = new HashMap<>();


        // 3자리 토큰발행행
        String token = RandomTokenUtil.getRandomToken(3);

        User hostUser = userRepository.findById(xUserId);
        Room room = roomRepository.findByIdAndUser(xRoomId, hostUser);
        if(room == null){
            log.error(ShareEventException.ResponseCode.E0007.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0007);
        }
        hostTotalMoney = hostUser.getMoney() - totalShareMoney;
        hostUser.setMoney(hostTotalMoney);
        userRepository.save(hostUser);

        // 돈나누기
        List<ShareEventDetail> shareEventDetails = divideMoney(totalShareMoney, totalSharePeople);

        ShareEvent shareEvent = ShareEvent.builder()
                .user(hostUser)
                .token(token)
                .room(room)
                .totalShareMoney(totalShareMoney)
                .totalSharePeople(totalSharePeople)
                .expired(false)
                .build();

        shareEvent = shareEventRepository.save(shareEvent);

        for (ShareEventDetail shareEventDetail : shareEventDetails) {
            shareEventDetail.setShareEvent(shareEvent);
            shareEventDetailRepository.save(shareEventDetail);
        }
        resultMap.put("token",token);
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> attendShareEvent(int xUserId, int xRoomId, String token) throws ShareEventException {
        int shareMoney = 0;
        Map<String, Object> resultMap = new HashMap<>();
        Random random = new Random();
        random.setSeed(new Date().getTime());
        ShareEventDetail shareEventDetail;
        ShareEvent shareEvent = new ShareEvent();
        Room room = new Room();

        List<Room> rooms = roomRepository.findByNum(roomRepository.findById(xRoomId).getNum());
        User receiveUser = userRepository.findById(xUserId);

        for(Room tempRoom: rooms){
            shareEvent = shareEventRepository.findByRoomAndToken(tempRoom,token);
            if(shareEvent != null){
                break;
            }
        }
        validateShareEvent(shareEvent, receiveUser, room);

        //해당 돈뿌리기 Detail중 랜덤으로 1개 뽑기
        List<ShareEventDetail> shareEventDetails = shareEventDetailRepository.findByShareEventIdAndUserIsNull(shareEvent.getId());
        if (shareEventDetails.size() != 1) {
            int randomNum = random.nextInt(shareEventDetails.size() - 1);
            shareEventDetail = shareEventDetails.get(randomNum);
            shareMoney = shareEventDetail.getShareMoneyDetail();
        } else {
            shareEventDetail = shareEventDetails.get(0);
            shareMoney = shareEventDetail.getShareMoneyDetail();
            shareEvent.setExpired(true);
            shareEventRepository.save(shareEvent);
        }

        //받는 user의 money 증가
        receiveUser.setMoney(receiveUser.getMoney() + shareMoney);
        shareEventDetail.setUser(receiveUser);

        //바뀐 user, eventDatail 저장
        shareEventDetailRepository.save(shareEventDetail);
        userRepository.save(receiveUser);

        resultMap.put("shareMoney",shareMoney);
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public ShareEventDto searchShareEvent (int xUserId, int xRoomId, String token) throws ShareEventException{

        List<ShareEventDetailDto> shareEventDetailDtos = new ArrayList<>();
        int completeShareMoney = 0 ;

        User hostUser = userRepository.findById(xUserId);
        Room room = roomRepository.findById(xRoomId);
        ShareEvent shareEvent = shareEventRepository.findByRoomAndTokenAndUser(room,token,hostUser);

        if(shareEvent == null){
            log.error(ShareEventException.ResponseCode.E0009.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0009);
        }

        long currentTime = new Date().getTime();
        long regTime = shareEvent.getRegDate().getTime();
        long diffTime = (currentTime - regTime) /  (24 * 60 * 60 * 1000);

        if(diffTime > 7){
            log.error(ShareEventException.ResponseCode.E0008.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0008);
        }

        List<ShareEventDetail> shareEventDetails = shareEvent.getShareEventDetails();

        Iterator iter = shareEventDetails.iterator();
        while(iter.hasNext()) {
            ShareEventDetail shareEventDetail = (ShareEventDetail) iter.next();
            if(shareEventDetail.getUser() == null){
                iter.remove();
            }else {
                ShareEventDetailDto shareEventDetailDto = new ShareEventDetailDto(shareEventDetail.getShareMoneyDetail(),shareEventDetail.getUser().getId());
                completeShareMoney += shareEventDetail.getShareMoneyDetail();
                shareEventDetailDtos.add(shareEventDetailDto);
            }
        }

        return new ShareEventDto(shareEvent.getRegDate(),shareEvent.getTotalShareMoney(),completeShareMoney,shareEventDetailDtos);
    }
    private List<ShareEventDetail> divideMoney(int totalShareMoney, int totalSharePeople) {

        List<ShareEventDetail> shareEventDetails = new ArrayList<>();

        int min = 1;
        int max = totalShareMoney;
        Random random = new Random();
        random.setSeed(new Date().getTime());

        for (int i = 0; i < totalSharePeople; i++) {
            ShareEventDetail shareEventDetail = new ShareEventDetail();
            int randomMoney = 0;
            if (i != totalSharePeople - 1) {
                randomMoney = random.nextInt(max - totalSharePeople - 1) + min;
            } else {
                randomMoney = max;
            }
            shareEventDetail.setShareMoneyDetail(randomMoney);
            shareEventDetails.add(shareEventDetail);

            max = max - randomMoney;
        }
        return shareEventDetails;
    }

    private void validateShareEvent( ShareEvent shareEvent, User user, Room room) {

        long checkAttend = 0;

        if(room == null){
            log.error(ShareEventException.ResponseCode.E0006.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0006);
        }

        if(shareEvent == null){
            log.error(ShareEventException.ResponseCode.E0004.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0004);
        }

        if(user == null){
            log.error(ShareEventException.ResponseCode.E0005.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0005);
        }

        long currentTime = new Date().getTime();
        long regTime = shareEvent.getRegDate().getTime();

        long diffTime = (currentTime - regTime) /  1000;
        if (shareEvent.isExpired() || diffTime > 600) {
            log.error(ShareEventException.ResponseCode.E0003.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0003);
        }
        if (user.getId() == shareEvent.getUser().getId()) {
            log.error(ShareEventException.ResponseCode.E0002.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0002);
        }

        checkAttend = shareEventDetailRepository.countByShareEventIdAndUserIs(shareEvent.getId(), user);

        if (checkAttend > 0) {
            log.error(ShareEventException.ResponseCode.E0001.getValue());
            throw new ShareEventException(ShareEventException.ResponseCode.E0001);
        }
    }

}
