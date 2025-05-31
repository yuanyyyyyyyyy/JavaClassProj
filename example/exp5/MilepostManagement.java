package com.example.exp5;

import java.util.Scanner;

import com.example.exp5.domain.Milepost;
import com.example.exp5.service.MilepostService;

public class MilepostManagement {
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("----------里程碑管理----------");
            System.out.println("1.添加里程碑  2.查询里程碑 3.编辑里程碑 4.删除里程碑");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入需要的操作：");
            int i = sc.nextInt();
            // 数据操作
            MilepostService ms = new MilepostService();
            switch (i) {
                case 1:
                    // 录入信息
                    Milepost milepost = ms.insertMilepostInfo();
                    ms.addMilepost(milepost);
                    break;
                case 2:
                    System.out.println("请输入里程碑名称，输入0显示所有：");
                    ms.getMilepost(sc.next());
                    break;
                case 3:
                    System.out.println("请输入需要编辑的里程碑编号：");
                    int mid = sc.nextInt();
                    // 编辑的信息
                    Milepost milepost2 = ms.insertMilepostInfo();
                    ms.editMilepost(milepost2, mid);
                    break;
                case 4:
                    System.out.println("请输入需要删除的里程碑编号：");
                    int id = sc.nextInt();
                    ms.delMilePost(id);
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }
}
