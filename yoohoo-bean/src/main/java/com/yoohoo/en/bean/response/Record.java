package com.yoohoo.en.bean.response;

import java.io.Serializable;

/**
 * Created By LiWenLong On 2018/8/6 16:22
 * E-Mail:it_lwl@163.com
 */
public class Record implements Serializable {

        //视频记录大小
        private String size;

        //http视频地址

        private String playpath;

        //https视频地址

        private String  https_playpath;

        //录制时间

        private  String duration;



        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPlaypath() {
            return playpath;
        }

        public void setPlaypath(String playpath) {
            this.playpath = playpath;
        }

        public String getHttps_playpath() {
            return https_playpath;
        }

        public void setHttps_playpath(String https_playpath) {
            this.https_playpath = https_playpath;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }


    @Override
    public String toString() {
        return "Record{" +
                "size='" + size + '\'' +
                ", playpath='" + playpath + '\'' +
                ", https_playpath='" + https_playpath + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
