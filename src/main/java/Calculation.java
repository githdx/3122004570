import com.hankcs.hanlp.HanLP;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculation {
    //分词
    public static List<String> participle(String txt){
        return HanLP.extractKeyword(txt, txt.length());
    }

    //hash
    public static List<String> Hash(List<String> list){
        List<String> hashList = new ArrayList<>();
        //将字符串数组的每个字符串转成256位的hash值
        for(String str:list){
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bytes=messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
                String integer =new BigInteger(1,bytes).toString(2);
                StringBuilder wordHash = new StringBuilder(integer);
                // hash值少于256位的，尾部补全
                if (wordHash.length() < 256) wordHash.append("0".repeat(256 - wordHash.length()));
                hashList.add(wordHash.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashList;
    }

    //降维
    public static void dimensionalityReduction(int[] weightVectorList){
        //权重值大于0则为1，反之为0
        for(int i=0;i<weightVectorList.length;i++){
            weightVectorList[i]=weightVectorList[i]>0?1:0;
        }
    }

    //加权
    public static int[][] weighted(List<String> hashList){
        int size= hashList.size();
        int[][] weightVectorList=new int[size][256];
        for(int i=0;i<size;i++) {
            String str = hashList.get(i);
            for (int j = 0; j < str.length(); j++) {
                //权重范围0-10，特征词在数组中位置越靠前权重越大
                int weight = 10 - 10 * i / size;
                //加权
                if (str.charAt(j) == '1') weightVectorList[i][j] = weight;
                else weightVectorList[i][j] = -weight;
            }
        }
        return weightVectorList;
    }

    //合并
    public static int[] merged(int[][] list){
        int[] weightVectorList=new int[256];
        for (int[] ints : list) {
            //System.out.println(arr.length);
            for (int j = 0; j < 256; j++) {
                weightVectorList[j] += ints[j];
            }
        }
        return weightVectorList;
    }

    //计算海明距离
    public static int calculateHammingDistance(int[] list1,int[] list2){
        int distance=0;
        System.out.println(list1.length);
        for(int i=0;i<list1.length;i++){
            if(list1[i]!=list2[i])distance++;
        }
        System.out.println(distance);
        return distance;
    }
}
