package com.example.exp5.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.exp5.domain.Milepost;
import com.example.exp5.utils.JDBCUtils;

/**
 * 实现对Milepost的CRUD
 */

public class MilepostService {

    // 输入界面
    public Milepost insertMilepostInfo() throws ParseException {
        Milepost milepost = new Milepost();

        System.out.println("请输入里程碑信息");
        Scanner scanner = new Scanner(System.in);
        System.out.println("里程碑名称：");
        milepost.setName(scanner.nextLine());
        System.out.println("发射时间(2000-10-10): ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        milepost.setLaunchtime(format.parse(scanner.next()));
        System.out.println("描述：");
        milepost.setDepict(scanner.next());
        System.out.println("状态：");
        milepost.setState(scanner.nextInt());

        return milepost;

    }

    // SQL操作 CRUD
    /**
     * 新增里程碑信息
     * 
     * @param milepost
     * @throws SQLException
     */
    public void addMilepost(Milepost milepost) throws SQLException {
        // 传统SQL操作：ResultSet / Statement / Connection
        // 使用Apache DbUtils进行数据库操作，把数据集与数据源进行解耦
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO milepost (name, launchtiem, depict, state) VALUES (?, ?, ?, ?)";
        Object[] params = {
                milepost.getName(), milepost.getLaunchtime(),
                milepost.getDepict(), milepost.getState()
        };

        int count = qr.update(sql, params);
        if (count < 1) {
            System.out.println("新增里程碑信息失败！");
        } else {
            System.out.println("新增里程碑信息成功！");
        }
    }

    /**
     * 查询里程碑信息
     * 
     * @param name
     * @throws SQLException
     */
    public void getMilepost(String name) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        // 查询所有和查询指定名字
        String sql;
        List<Milepost> milepostList;
        if ("0".equals(name)) {
            sql = "SELECT * FROM milepost WHERE state=0";
            milepostList = qr.query(sql, new BeanListHandler<Milepost>(Milepost.class));
        } else {
            sql = "SELECT * FROM milepost WHERE name=? AND state=0";
            milepostList = qr.query(sql, new BeanListHandler<Milepost>(Milepost.class), name);
        }

        // 输出结果集
        if (milepostList != null) {
            for (Milepost milepost : milepostList) {
                System.out.println(milepost);
            }
        } else {
            System.out.println("查询失败！");
        }

    }

    /**
     * 修改里程碑信息
     * @param milepost
     * @param mid
     * @throws SQLException
     */
    public void editMilepost(Milepost milepost, int mid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "UPDATE milpost SET name=?, launchtime=?, depict=?, state=? WHERE id=?";
        Object[] params = {
                milepost.getName(), milepost.getLaunchtime(),
                milepost.getDepict(), milepost.getState(),
                mid
        };
        int count = qr.update(sql, params);
        if(count < 1){
            System.out.println("修改里程碑信息失败！");
        }else{
            System.out.println("修改里程碑信息成功！");
        }
    }

    /**
     * 删除里程碑信息
     * @param mid
     * @throws SQLException
     */
    public void delMilePost(int mid) throws SQLException{
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "DELETE FROM milepost WHERE id=?";
        int count = qr.update(sql, mid);
        if(count < 1){
            System.out.println("删除里程碑信息失败！");
        }else{
            System.out.println("删除里程碑信息成功！");
        }
    }
}


