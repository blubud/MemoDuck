package me.lkhz.memoduck.memo.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MemoRepository {

    private static MemoRepository INSTANCE = null;
    private MemoRepository(){
    }

    public static void makeInstance(){
        if(INSTANCE == null){
            INSTANCE = new MemoRepository();
        }
    }
    public static MemoRepository getInstance(){
        if(INSTANCE == null){
            makeInstance();
        }
        return INSTANCE;
    }

    public List<MemoItem> getMemoItems(){
        List<MemoItem> result = new ArrayList<>();
        result.add(new MemoItem("20210403-0000", "테스트111111111111111111111111111111111111111111111111111111111111111"));
        result.add(new MemoItem("20210403-0001", "22222222222222222222222222222222222222222222222222222222222222222222222"));
        result.add(new MemoItem("20210403-0002", "33333333333333333333333333333333333333333333333"));
        result.add(new MemoItem("20210403-0003", "444444444"));
        return result;
    }
}
