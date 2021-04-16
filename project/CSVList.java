package com.codebind;

import java.util.*;

public class CSVList {

    private List<String> globalList = new ArrayList<>();

    public void emptyList() {
        globalList.clear();
    }

    public void add(String str)
    {
        globalList.add(str);
    }

    public int size() {
        return globalList.size();
    }

    public void copy(CSVList other)
    {
        emptyList();
        for(int i = 0; i < other.size();i++)
        {
            this.add(other.get(i));
        }
    }

    public String get(int i) {
        // TODO Auto-generated method stub
        return globalList.get(i);
    }
}
