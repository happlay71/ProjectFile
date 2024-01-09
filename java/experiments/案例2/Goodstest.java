package experiments.案例2;

import java.util.Scanner;

public class Goodstest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //华为手机
        String Huaweiname = "华为";
        double ChiCun = 5.5;
        double JiaGe = 3688.88;
        String PeiZhi = "8 + 128g 全面刘海屏";
        //小米手机
        String Xiaominame = "小米";
        double XiaomiChiCun = 5.0;
        double XiaomiJiaGe = 2988.88;
        String XiaomiPeiZhi = "4 + 64g 全面屏";

        //华为入库
        System.out.println("品牌型号:" + Huaweiname);
        System.out.println("尺寸:" + ChiCun);
        System.out.println("价格:" + JiaGe);
        System.out.println("配置:" + PeiZhi);

        System.out.println("请输入" + Huaweiname + "手机的库存:");
        int KuCun = in.nextInt();
        double ShangPinZongJia = KuCun * JiaGe;
        System.out.println("库存"+ Huaweiname + "手机的总金额:" + ShangPinZongJia );

        //小米入库
        System.out.println("品牌型号:" + Xiaominame);
        System.out.println("尺寸:" + XiaomiChiCun);
        System.out.println("价格:" + XiaomiJiaGe);
        System.out.println("配置:" + XiaomiPeiZhi);

        System.out.println("请输入" + Xiaominame + "手机的库存:");
        int XiaomiKuCun = in.nextInt();
        double XiaomiZongJia = XiaomiKuCun * XiaomiJiaGe;
        System.out.println("库存" + Xiaominame + "手机的总金额" + XiaomiZongJia);

        //打印
        System.out.println("--------库存清单--------");
        System.out.println("品牌型号\t尺寸\t\t价格\t\t\t配置\t\t\t\t库存数量\t\t总价");
        System.out.println(Huaweiname + "\t\t" + ChiCun + "\t\t" + JiaGe + "\t\t" + PeiZhi + "\t" + KuCun + "\t\t" + ShangPinZongJia);
        System.out.println(Xiaominame + "\t\t" + XiaomiChiCun + "\t\t" + XiaomiJiaGe + "\t\t" + XiaomiPeiZhi + "\t\t" + XiaomiKuCun + "\t\t" + XiaomiZongJia);
        System.out.println("------------------------");
        int Zong = KuCun + XiaomiKuCun;
        double ZongJiaGe = ShangPinZongJia + XiaomiZongJia;
        System.out.println("商品总库存:" + Zong );
        System.out.println("商品总金额:" + ZongJiaGe + "￥");
    }
}
