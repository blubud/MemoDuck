package me.lkhz.memoduck.memo.repository;

import java.util.ArrayList;

import me.lkhz.memoduck.memo.repository.memo.MemoItem;

public interface MemoSource {

    interface LoadAlarmCallback{
        void onLoadAlarm(ArrayList<MemoItem> alarmList);
    }

    void getMemoItems(LoadAlarmCallback callback);

}