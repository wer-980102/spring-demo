package com.wer.parking.utlis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公共的数据类型
 */
@Component
public class CommonUtils {

    /**
     * redis 缓存参数
     **/
    public static final Integer READ_MIN = 0;
    public static final Integer READ_MAX = -1;
    public static final Integer FAILURE_TRIME = 26;

    public static final String READ_YEAR = "year";
    public static final String READ_MONTH = "month";
    public static final String READ_DAY = "day";
    public static final String READ_ORDER = "orderInfo";
    public static final String READ_COME_VEHICLE = "packComeVehicle";
    public static final String READ_ENTER_VEHICLE = "packEnterVehile";


    /**
     * redis 缓存参数
     **/
    public static final String TENANID_VALUE = "10000000";

    /**
     * 0 类型
     **/
    public static final String KEY_TOP = "KEY_TOP";
    public static final String TAI_AN = "TAI_AN";


/** 0所有设备 1收费电脑/缴费机 2相机 3LED屏4出入口道闸 999监控相机通道 1000出口岗亭 1001主相机
 * 1002辅相机  1003网络屏 1004出口缴费机 1005场内缴费机 1006LED入口取票机 1007中央岗亭1008LCD入口取票机
 * 1010LED网络屏 1011tts语音播报1012LCD屏 1013移动岗亭 1015车身监控相机 1016LCD广告屏 1017网络对讲机 1032车流控制设备 1034道闸控制器 1099第三方识别设备*/
    /**
     * 设备类型
     */
    public static String getDeviceType(String deviceType) {
        switch (deviceType) {
            case "1":
                return "收费电脑/缴费机";
            case "2":
                return "相机";
            case "3":
                return "LED屏";
            case "4":
                return "出入口道闸";
            case "999":
                return "监控相机通道";
            case "1000":
                return "出口岗亭";
            case "1001":
                return "主相机";
            case "1002":
                return "辅相机";
            case "1003":
                return "网络屏";
            case "1004":
                return "出口缴费机";
            case "1005":
                return "场内缴费机";
            case "1006":
                return "LED入口取票机";
            case "1007":
                return "中央岗亭";
            case "1008":
                return "LCD入口取票机";
            case "1010":
                return "LED网络屏";
            case "1011":
                return "tts语音播报";
            case "1012":
                return "LCD屏";
            case "1013":
                return "移动岗亭";
            case "1015":
                return "车身监控相机";
            case "1016":
                return "LCD广告屏";
            case "1017":
                return "网络对讲机";
            case "1032":
                return "车流控制设备";
            case "1034":
                return "道闸控制器";
            case "1099":
                return "第三方识别设备";
            default:
                return "其他";
        }
    }

    /**
     * 获取所有车牌类型
     */
    public static String getCarPlateType(String carPlateType) {
        switch (carPlateType) {
            case "100+":
                return "黑名单车";
            case "0":
                return "临时车";
            case "1":
                return "VIP车";
            case "2":
                return "月租车";
            case "3":
                return "充值车";
            case "4":
                return "时租车";
            case "5":
                return "产权车";
            case "6":
                return "计次车";
            case "7":
                return "贵宾卡";
            case "8":
                return "员工卡";
            case "9":
                return "大客车";
            case "100":
                return "预约车";
            default:
                return "其他";
        }
    }

    /**
     * 出场放行类型 0.正常放行 1.免费放行2.异常放行3.遥控放行4.跟车放行5.虚拟放行6.长抬放行7.临时车异常放行
     */
    public static String getQuitType(String quitType) {
        switch (quitType) {
            case "0":
                return "正常放行";
            case "1":
                return "免费放行";
            case "2":
                return "异常放行";
            case "3":
                return "遥控放行";
            case "4":
                return "跟车放行";
            case "5":
                return "虚拟放行";
            case "6":
                return "长抬放行";
            case "7":
                return "临时车异常放行";
            default:
                return "其他";
        }
    }

    /**
     * 支付： 1现金 2电子现金  3 微信 4 支付宝  8 其它  5  线上银联 6  ETC支付
    *
     */
    public static String getPayChanel(String payChanel) {
        switch (payChanel) {
            case "1":
                return "现金";
            case "2":
                return "电子现金";
            case "3":
                return "微信";
            case "4":
                return "支付宝";
            case "5":
                return "线上银联";
            case "6":
                return "ETC支付";
            case "8":
                return "其它";
            default:
                return "其它";
        }
    }


    /**
     * 支付： 1现金 2电子现金  3 微信 4 支付宝  8 其它  5  线上银联 6  ETC支付
     *
     */
    public static String getPayTypeChanel(String payChanel) {
        switch (payChanel) {
            case "现金":
                return "1";
            case "电子现金":
                return "2";
            case "微信":
                return "3";
            case "支付宝":
                return "4";
            case "线上银联":
                return "5";
            case "ETC支付":
                return "6";
            case "其它":
                return "8";
            default:
                return "8";
        }
    }

    /**
     *  支付规则： 1现金 2电子现金  3 微信 4 支付宝  8 其它  5  线上银联 6  ETC支付
     *  泰安-支付规则转换 支付方式(1.现金、2.微信、3.支付宝、4.网银、5.电子钱包、6.优免卷7.余额
     */
    public static String getPayTypeTransform(Integer payChanel) {
        switch (payChanel) {
            case 1:
                return "1";
            case 2:
                return "3";
            case 3:
                return "4";
            case 4:
                return "5";
            case 5:
                return "2";
            case 7:
                return "6";
            case 6:
                return "8";
            default:
                return "8";
        }
    }

    /**
     * 4	微信平台
     * 5	APP（安卓/IOS）
     * 1000	其他
     * 1001	场内岗亭
     * 1002	出口岗亭
     * 1003	商家终端机
     * 1004	出口缴费机
     * 1005	场内缴费机
     * 1006	找车缴费机
     * 1007	挂壁式自助机
     * 1008	券发放机
     * 1009	充电桩
     * 1010	线上支付
     * 1011	出口贴码
     * 1012	后付费
     * 1013	智慧通行无感
     * 1014	Upark无感
     * 1015	建行无感
     * 1016	招行无感
     * 1017	工行无感
     * 1018	农行无感
     * 1019	一网通支付
     * 1020	银行协议扣款
     * 1021	免密支付
     * 1022	信用付扣款
     * 1029	无感支付
     * 6666	4S服务平台
     */
    public static String getPayClient(String payClient) {
        switch (payClient) {
            case "4":
                return "微信平台";
            case "5":
                return "APP";
            case "1000":
                return "其他";
            case "1001":
                return "场内岗亭";
            case "1002":
                return "出口岗亭";
            case "1003":
                return "商家终端机";
            case "1004":
                return "出口缴费机";
            case "1005":
                return "场内缴费机";
            case "1006":
                return "挂壁式自助机";
            case "1007":
                return "券发放机";
            case "1008":
                return "充电桩";
            case "1009":
                return "线上支付";
            case "1010":
                return "出口贴码";
            case "1011":
                return "后付费";
            case "1012":
                return "智慧通行无感";
            case "1013":
                return "Upark无感";
            case "1014":
                return "建行无感";
            case "1015":
                return "招行无感";
            case "1016":
                return "工行无感";
            case "1017":
                return "农行无感";
            case "1018":
                return "一网通支付";
            case "1019":
                return "银行协议扣款";
            case "1020":
                return "免密支付";
            case "1021":
                return "信用付扣款";
            case "1022":
                return "无感支付";
            case "6666":
                return "4S服务平台";
            default:
                return "其他";
        }
    }

    /**
     * 0 钱 1 月,2 天,3 小时,4次
     */
    public static String getChargeType(String paymentKind) {
        switch (paymentKind) {
            case "0":
                return "钱";
            case "1":
                return "月";
            case "2":
                return "天";
            case "3":
                return "小时";
            case "4":
                return "次";
            default:
                return "其他";
        }
    }

    /**
     * 转换支付方式
     *
     * @param param
     * @return
     */
    public static String getPayType(String param) {
        switch (param) {
            case "1001":
                return "1";
            case "1002":
                return "8";
            case "1003":
                return "2";
            case "1004":
                return "3";
            case "1005":
                return "4";
            case "1006":
                return "8";
            case "1000":
                return "8";
            case "1007":
                return "5";
            case "1008":
                return "6";
            case "1009":
                return "8";
            case "1010":
                return "8";
            case "1011":
                return "8";
            case "1012":
                return "8";
            case "1013":
                return "8";
            case "1014":
                return "8";
            case "1020":
                return "8";
            default:
                return "8";
        }
    }

    /**
     * 转换支付方式名称
     *
     * @param param
     * @return
     */
    public static String getPayTypeName(String param) {
        switch (param) {
            case "1":
                return "现金";
            case "2":
                return "电子现金";
            case "3":
                return "微信";
            case "4":
                return "支付宝";
            case "5":
                return "线上银联";
            case "6":
                return "ETC支付";
            case "8":
                return "其它";
            default:
                return "其他";
        }
    }

    /**
     * 转换支付方式名称
     *
     * @param param
     * @return
     */
    public static String getDeviceAlarmType(String param) {
        switch (param) {
            case "1":
                return "1";
            case "2":
                return "1";
            case "3":
                return "1";
            case "4":
                return "1";
            case "5":
                return "1";
            case "6":
                return "1";
            case "8":
                return "1";
            case "9":
                return "1";
            case "10":
                return "1";
            case "11":
                return "1";
            case "12":
                return "1";
            case "13":
                return "1";
            case "14":
                return "1";
            case "15":
                return "2";
            case "16":
                return "2";
            case "17":
                return "2";
            case "18":
                return "2";
            case "19":
                return "2";
            case "20":
                return "2";
            case "21":
                return "2";
            case "22":
                return "3";
            case "23":
                return "1";
            case "24":
                return "1";
            case "25":
                return "1";
            case "26":
                return "1";
            case "27":
                return "3";
            case "28":
                return "1";
            default:
                return "3";

        }
    }


    /**
     * 转换支付方式名称
     *
     * @param param
     * @return
     */
    public static String getParkFormat(String param) {
        switch (param) {
            case "1":
                return "社区和企事业单位";
            case "2":
                return "商业综合体";
            case "3":
                return "交通枢纽";
            case "4":
                return "公共设施";
            case "5":
                return "商务写字楼";
            default:
                return "其他";
        }
    }

/** 获取*/
    /**
     * 计算两个集合差数
     **/
    public static <t> List getOthernessInfo(List param, List param1) {
        List<t> c = null;
        List<t> d = null;
        c = new ArrayList(param);
        c.retainAll(param1); // 得到  a, b 的交集。
        d = new ArrayList(param);
        d.addAll(param1); // 合并 a, b 值到 d 中。
        d.removeAll(c);// 去掉交集 c 中的所有条目。留下只出现在a 或 b 中的条目。
        return d;
    }

    /**
     * 截取小数点后两位
     */
    public static String getRateStr(String rateStr) {

        int i = rateStr.indexOf(".");
        if (i != -1) {
            //获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");
            System.out.println(num + "=============");

            //获取小数点后面的数字 是否有两位 不足两位补足两位
            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");
            if (afterData.length() < 2) {
                afterData = afterData + "0";
            }
            return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
        } else {
            rateStr = rateStr + ".00";
            return rateStr;
        }
    }

    /**
     * LocalDateTime获取时间戳
     */
    public static Long getTimestamp(String time1, String time2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = null;
        if (time1 != null && time2 != null) {
            try {
                time = df.parse(time2).getTime() - df.parse(time1).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Date date = new Date();
            try {
                time = date.getTime() - df.parse(time2).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return time;
    }

    /**
     * 判空
     */
    public static boolean Empty(Object obj) {
        if (obj != null && !"".equals(obj)) {
            return false;
        }
        return true;
    }

    /**
     * 生成随机数
     *
     * @return
     */
    public static String getRandom() {
        int max = 1000000, min = 1;
        int ran2 = (int) (Math.random() * (max - min) + min);
        return String.valueOf(ran2);
    }

    /**
     * 保留后两位小数
     *
     * @param param
     * @return
     */
    public static String getDecimaFormat(double param) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(param);
    }

    /**
     * 根据逗号截取字符串
     * @param param
     * @return
     */
    public static List<String> getCutOut(String param){
        List<String> paramList = new ArrayList<>();
        String substring = param.substring(0, param.length() - 1);
        String[] split = substring.split(",");
        for (String string2 : split) {
            paramList.add(string2);
        }
        return paramList;
    }

    /**
     *  base64编码
     * @param url
     * @return
     * @throws Exception
     */
    public static  String getBase64(String url)  {
         String encodedText = null;
        try {
            final Base64.Encoder encoder = Base64.getEncoder();
            final byte[] textByte = url.getBytes("UTF-8");
            //编码
             encodedText = encoder.encodeToString(textByte);
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodedText;
    }


    /**
     * 匹配Ip
     * @param url
     * @return
     */
    public static  String getVideoIp(String url)  {
        switch (url){
            case "172.19.2.2":
                return "172.19.2.2";
            case "172.19.2.3":
                return "172.19.2.3";
            case "172.19.2.102":
                return "172.19.2.102";
            case "172.19.2.103":
                return "172.19.2.103";
            case "172.19.8.102":
                return "172.19.8.102";
            case "172.19.8.103":
                return "172.19.8.103";
            default:
                return null;
        }
    }
    /**
     * 匹配车场
     * @param parkingId
     * @return
     */
    public static  boolean getVideoParkingId(String parkingId)  {
        boolean flag =true;
        switch (parkingId){
            case "731014716":
                return flag ;
            case "731014717":
                return flag;
            case "739000442":
                return flag;
            case "512020806":
                return flag;
            case "512020805":
                return flag;
            case "519006833":
                return flag;
            case "731014824":
                return flag;
            default:
                return false;
        }

    }
    /**
     * 匹配车辆类型
     * @param CarTypeName
     * @return
     */
    public static  Integer getCarTypeName(String CarTypeName)  {
        switch (CarTypeName){
            case "摩托车":
                return 1 ;
            case "小车":
                return 2;
            case "大车":
                return 3;
            case "超大车":
                return 4;
            default:
                return 0;
        }
    }
    /**
     * 匹配车辆类型
     * @param carPlateType
     * @return
     */
    public static Integer getCarPlateTypeNum(String carPlateType)  {
        switch (carPlateType){
            case "1":
                return 0 ;
            case "2":
                return 2;
            case "3":
                return 7;
            default:
                return 0;
        }
    }
    /**
     *  泰安的固定车场，伪装去请求
     * @return
     */
    public static List<String> getParkingIdInfo(){
        List<String> args = new LinkedList<>();
        try{
            ClassPathResource classPathResource = new ClassPathResource("parkingId.txt");
            InputStream inputStream = classPathResource.getInputStream();
            byte b[]=new byte[inputStream.available()];
            inputStream.read(b);
            inputStream.close();

            List<String> cutOut = getCutOut(new String(b));
            cutOut.stream().forEach(str ->{
                args.add(str);
            });
            System.out.println("读取到的内容是："+args.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return args;
    }


}





