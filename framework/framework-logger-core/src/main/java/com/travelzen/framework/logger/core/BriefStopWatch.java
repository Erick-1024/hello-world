package com.travelzen.framework.logger.core;

import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;

/**
 * 用来记录执行之间的“秒表”，利用Perf4j实现。
 * @author chengwen.li
 */
public class BriefStopWatch {

    private String defaultTag;
    private StopWatch sw;

    public BriefStopWatch() {
        StackTraceElement st = null;
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            if (!ste.getClassName().equals(BriefStopWatch.class.getName())
                    && !ste.getClassName().equals("java.lang.Thread")) {
                st = ste;
                break;
            }
        }
        if (st != null) {
            this.defaultTag = st.getClassName() + '.' + st.getMethodName();
        } else {
            this.defaultTag = "invoker info missing";
        }
        this.sw = new Slf4JStopWatch(defaultTag);
    }

    public BriefStopWatch(String tag) {
        this.defaultTag = tag;
        sw = new Slf4JStopWatch(tag);
    }

    public BriefStopWatch start() {
        sw.start();
        return this;
    }

    public BriefStopWatch start(String tag) {
        sw.start(tag);
        return this;
    }

    public BriefStopWatch start(String tag, String msg) {
        sw.start(tag, msg);
        return this;
    }

    public BriefStopWatch lap() {
        sw.lap(this.defaultTag);
        return this;
    }

    public BriefStopWatch lap(String tag) {
        sw.lap(tag);
        return this;
    }

    public BriefStopWatch lap(String tag, String msg) {
        sw.lap(tag, msg);
        return this;
    }

    public BriefStopWatch lapWithMsg(String msg) {
        sw.lap(this.defaultTag, msg);
        return this;
    }

    public BriefStopWatch stop() {
        sw.stop();
        return this;
    }

    public BriefStopWatch stop(String tag) {
        sw.stop(tag);
        return this;
    }

    public BriefStopWatch stop(String tag, String msg) {
        sw.stop(tag, msg);
        return this;
    }

    public BriefStopWatch stopWithMsg(String msg) {
        sw.stop(this.defaultTag, msg);
        return this;
    }

    public static BriefStopWatch startNew() {
        return new BriefStopWatch().start();
    }

    public static BriefStopWatch startNew(String tag) {
        return new BriefStopWatch(tag);
    }

    public static BriefStopWatch startNew(String tag, String msg) {
        BriefStopWatch bsw = new BriefStopWatch(tag);
        bsw.start(tag, msg);
        return bsw;
    }

}
