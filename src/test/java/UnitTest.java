import org.junit.Test;

public class UnitTest {
    @Test
    public void mainTest(){
        String test1= "../../src/resources/orig.txt";
        String test2= "../../src/resources/orig_0.8_add.txt";
        String test3= "../../src/resources/orig_0.8_del.txt";
        String test4= "../../src/resources/orig_0.8_dis_1.txt";
        String test5= "../../src/resources/orig_0.8_dis_10.txt";
        String test6= "../../src/resources/orig_0.8_dis_15.txt";
        String test7= "../../src/resources/test_1.txt";
        String test8= "../../src/resources/test_2.txt";
        String test9= "../../src/resources/test_3.txt";
        String test10= "../../src/resources/test_4.txt";
        String ans1="../../src/resources/answer_test.txt";

        System.out.println("测试一结果：");
        test(test1,test2,ans1);
        System.out.println("测试二结果：");
        test(test1,test3,ans1);
        System.out.println("测试三结果：");
        test(test1,test4,ans1);
        System.out.println("测试四结果：");
        test(test1,test5,ans1);
        System.out.println("测试五结果：");
        test(test1,test6,ans1);
        System.out.println("测试六结果：");
        test(test1,test7,ans1);
        System.out.println("测试七结果：");
        test(test1,test8,ans1);
        System.out.println("测试八结果：");
        test(test1,test9,ans1);
        System.out.println("测试九结果：");
        test(test1,test10,ans1);
        System.out.println("测试十结果：");
        test(test4,test5,ans1);
    }

    public static void test(String path1,String path2,String path3){
        String txt1,txt2;
        //文件打开失败，结束
        if((txt1=TxtHandle.txtRead(path1))==null||(txt2=TxtHandle.txtRead(path2))==null)return;
        //获取txt文本的SimHash值
        int[] weightVectorList1,weightVectorList2;
        //文本内容过短，结束
        if((weightVectorList1=Calculation.getSimHash(txt1))==null||(weightVectorList2=Calculation.getSimHash(txt2))==null)return ;
        //计算海明距离
        int distance= Calculation.calculateHammingDistance(weightVectorList1,weightVectorList2);
        //计算论文相似度
        double similarity= Calculation.calculateHammingSimilarity(distance);
        System.out.println("论文相似度为："+similarity+"%");
        //将结果写入文件
        TxtHandle.txtWrite(path3,similarity);
    }


}
