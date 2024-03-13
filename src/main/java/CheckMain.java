public class CheckMain {
    public static void main(String[] args) {
        //1.读取txt文件
        String originTxt,copyTxt;
        if((originTxt=TxtHandle.txtRead(args[0]))==null||(copyTxt=TxtHandle.txtRead(args[1]))==null)return;
        //2.获取SimHash
        int[] weightVectorListO,weightVectorListC;
        if((weightVectorListO=Calculation.getSimHash(originTxt))==null||(weightVectorListC=Calculation.getSimHash(copyTxt))==null)return ;
        //3.计算海明距离
        int distance=Calculation.calculateHammingDistance(weightVectorListO,weightVectorListC);
        //4.计算论文相似度
        double similarity=Calculation.calculateHammingSimilarity(distance);
        System.out.println("论文相似度："+similarity+"%");
        //5.将结果写入文件
        TxtHandle.txtWrite(args[2],similarity);
    }
}
