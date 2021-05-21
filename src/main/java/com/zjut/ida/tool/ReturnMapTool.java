package com.zjut.ida.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/11/23.
 */
public class ReturnMapTool{

    public static <T>Map<String,Object> returnMap(List<T> list,Integer page,Integer size){
        Map<String,Object> map=new HashMap<>();
        int allSize=list.size();//查询到的总的论文数量
        int index=page*size;//分页的首页位置
        int last=page*size+size;//分页的末尾位置
        int allPage=(allSize%size==0)?(allSize/size):(allSize/size)+1;//总共分出的页数
        if(page<allPage){
            map.put("list",list.subList(index,last>allSize?allSize:last));
        }else{
            map.put("list",new ArrayList<>());
        }
        map.put("allPage",allPage);
        map.put("hasNext",page+1<allPage);
        map.put("isFirst",page==0);
//        System.out.println("allPage"+allPage);
//        System.out.println("hasNext"+(page+1<allPage));
//        System.out.println("isFirst"+(page==0));
//        System.out.println("allSize"+allSize);
        System.out.println();
        return map;
    }

}
