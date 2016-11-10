package com.travelzen.framework.util;

/**
 * SM 表示具体的提供服务技术的特有的model类型（如thrift中的TUser）。
 * VO 表示S所对应的VO类（如User类）
 *
 * @author chengwen.li
 */
public interface ModelConverter <SM, VO> {

    /**
     * 从 vo 到 server model，（如 User -> TUser）
     */
    SM vo2sm(VO vo);

    /**
     * 从 server model 到 vo，（如 TUser -> User）
     */
    VO sm2vo(SM sm);

    Class<VO> getVOClass();

    Class<SM> getSMClass();

}
