package com.example.exp1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 会议预定系统 [业务逻辑]
 * @author yuanyou
 * @since 2025-03-03
 */
public class ReservationSystem {
    //数据的结构
    //会议室: 固定数据集合 和 可变数据集合 --> 泛型集合
    // private MeetingRoom[] meetingRooms; // 数组 - 固定
    private List<MeetingRoom> meetingRooms; // 会议室信息
    private List<Reservation> reservations; // 预定信息

    //构造函数
    public ReservationSystem(List<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
        this.reservations = new ArrayList<>();
    }

    /**
     * 查看所有会议室
     */
    public void showAllMeetingRooms() {
        System.out.println("---所有会议室列表---");
        System.out.println("名称       容纳人数     多媒体设备");
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom);
        }
        System.out.println("-------------");
    }
    /**
     * 预定会议室
     * 
     * 多用快捷键： VS Code
     * Ctrl + S
     * Alt + .
     * Shift + Alt + F
     */
    public void reserveMeetingRoom() {
        // 遍历可用会议室
        for (int i = 0; i < meetingRooms.size(); i++) {
            MeetingRoom room = meetingRooms.get(i);
            System.out.println((i + 1) + ". " + room.getName()
                    + "（容纳人数：" + room.getCapacity() + ", 多媒体设备："
                    + (room.hasMedia() ? "有" : "无") + ")");
        }
        // 选择会议室
        System.out.print("请选择要预定的会议室");
        Scanner sc = new Scanner(System.in);
        int roomNo = sc.nextInt();
        MeetingRoom selectedRoom = meetingRooms.get(roomNo - 1);
        // 选择约定日期
        System.out.print("已选择" + selectedRoom.getName() + ",");
        System.out.print("请选择预定日期(yyyy-MM-dd):");
        // 读入日期字符串
        String dateStr = sc.next();
        // 转换
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 读入时间字符串
        System.out.print("请选择预定时间段（例如：10:00-11:00）:");
        String timeStr = sc.next();
        String[] time = timeStr.split("-");
        // 转换
        LocalTime starTime = LocalTime.parse(time[0], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime endTime = LocalTime.parse(time[1], DateTimeFormatter.ofPattern("HH:mm"));

        // 判断该会议室该时间段是否被预定
        boolean avaiable = isAvaiable(selectedRoom, date, starTime, endTime);
        if(avaiable){  // 未被预定
            // 创建预定信息
            Reservation reservation = new Reservation(selectedRoom, date, starTime, endTime);
            // 未来：该信息存入到数据库或缓存服务中
            // 当前加入到预定集合中
            reservations.add(reservation);
            System.out.println("预定成功");
        }
    }
    /**
     * 判断该会议室该时间段是否被预定
     * 
     * @param selectedRoom 会议室
     * @param date         预定日期
     * @param startTime    预定开始时间
     * @param endTime      预定结束时间
     * @return boolean 是否预定
     */
    public boolean isAvaiable(MeetingRoom selectedRoom, LocalDate date, LocalTime startTime, LocalTime endTime) {
        // 默认返回可用
        boolean avaiable = true;

        // 获取预定情况
        // 集合 --> 流形式访问
        List<Reservation> reservationList = reservations.stream()
                .filter(r -> r.getMeetingRoom().equals(selectedRoom))
                .toList();
        if (reservationList.isEmpty()) {
            return true;
        }

        // 会议室找到，预定时间是否已被预定
        for (Reservation r : reservationList) {
            // 日期是否已被预定
            if (date.equals(r.getDate())) {
                // 时间段是否被预定
                if (startTime.isBefore(r.getEndTime())
                        && endTime.isAfter((r.getStartTime()))) {
                    System.out.println("该时间段会议室已被预定，请选择其他时间段或会议室");
                    avaiable = false;
                }
            }
        }

        return avaiable;
    }
    /**
     * 展示会议室预定情况
     */
    public void showResInfo() {
        System.out.println("---所有预定信息---");
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getName());
            List<Reservation> roomReservations = reservations.stream()
                    .filter(r -> r.getMeetingRoom().equals(meetingRoom))
                    .collect(Collectors.toList());
            for (Reservation reservation : roomReservations) {
                System.out.println(reservation);
            }
            if(roomReservations == null){
                System.out.println("该会议室无预定信息");
            }
        }
        System.out.println("------------");
    }
    /**
     * 查看某时间段内可用的会议室
     */
    public void selectRoomByTime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请按日期格式输入(yyyy-MM-dd)");
        String dateStr = sc.next();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("请输入时间段(例如：09:00-10:00)");
        String timeStr = sc.next();
        String[] time = timeStr.split("-");
        LocalTime startTime = LocalTime.parse(time[0], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime endTime = LocalTime.parse(time[1], DateTimeFormatter.ofPattern("HH:mm"));
        // 指定时间与已预定时间冲突的会议室
        List<Reservation> conflictList = reservations.stream()
                .filter(r -> date.equals(r.getDate())
                        && startTime.isBefore(r.getEndTime())
                        && endTime.isAfter(r.getStartTime()))
                .collect((Collectors.toList()));
        List<MeetingRoom> conflictRoom = conflictList.stream()
                .map(Reservation::getMeetingRoom)
                .collect(Collectors.toList());
        // 输出所有与指定时间不冲突的预定信息
        System.out.println("名称       容纳人数     多媒体设备");
        for(MeetingRoom meetingRoom : meetingRooms){
            if(!conflictRoom.contains(meetingRoom)){
                System.out.println(meetingRoom);
            }
        }
    }
public class TestSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始化会议室
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        meetingRooms.add(new MeetingRoom("会议室A-101", 20, true));
        meetingRooms.add(new MeetingRoom("会议室A-201", 15, true));
        meetingRooms.add(new MeetingRoom("会议室B-301", 40, true));
        meetingRooms.add(new MeetingRoom("会议室C-401", 20, false));
        meetingRooms.add(new MeetingRoom("会议室D-501", 30, false));

        ReservationSystem rSystem = new ReservationSystem(meetingRooms);
        System.out.println("---欢迎进入会议室预定系统---");
        System.out.println("1. 查看所有会议室");
        System.out.println("2. 预定会议室");
        System.out.println("3. 查看预定信息");
        System.out.println("4. 查看指定时间可用的会议室");
        System.out.println("5. 退出系统");

        while (true) {
            System.out.print("请输入数字，选择功能：");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    rSystem.showAllMeetingRooms();
                    break;
                case 2:
                    rSystem.reserveMeetingRoom();
                    break;
                case 3:
                    rSystem.showResInfo();
                    break;
                case 4:
                    rSystem.selectRoomByTime();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
}