package GinkgoStack.P22_AnalysisInJava3Third.C1;

public class TestMemoryCell
{
    public static void main( String [ ] args )
    {
        MemoryCell<Integer> m = new MemoryCell<>( );

        m.write( 5 );
        System.out.println( "Contents are: " + m.read( ) );

        MemoryCell<Integer> cell1  = new MemoryCell<>();
        cell1.write(1);

        Object cell = cell1;
        //要报错的，不能强制转换
//        MemoryCell<String> cell2 = (MemoryCell<String>)cell1;
//        String s = cell2.read();

        //参数化类型的数组的实例化是非法的
//            MemoryCell<String>[] arr1 = new MemoryCell<String>[10];
        MemoryCell<Double> doubleMemoryCell = new MemoryCell<>();
        doubleMemoryCell.write(4.5);
    }
}