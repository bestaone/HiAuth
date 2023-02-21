package cn.hiauth.mgr.common;

import java.util.List;

public interface IdGenerator {

    Long generate();

    List<Long> generateBatch(int count);

}