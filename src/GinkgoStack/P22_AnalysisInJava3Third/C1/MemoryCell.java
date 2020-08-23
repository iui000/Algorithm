package GinkgoStack.P22_AnalysisInJava3Third.C1;// com.GinkgoStack.C1.MemoryCell class
//  Object read( )         -->  Returns the stored value
//  void write( Object x ) -->  x is stored

public class MemoryCell<AnyType> {
    // Private internal data representation
    private AnyType storedValue;

//    在一个泛型类中，static方法和static域均不可引用类的类型变量，
//    //因为在类型擦除后类型变量就不存在了。另外，由于实际上只存在一个原始的类，
//    //因此static域在该类的诸泛型实例之间是共享的。
//    private static AnyType value;

//    不能创建一个泛型类型的实例。如果T是一个类型变量，则语句
//    T obj=new T();//右边是非法的
//    是非法的。T由它的限界代替，这可能是object(或甚至是抽象类）,因此对new的调用没有意义。
//    private AnyType anyType = new AnyType();

//    也不能创建一个泛型的数组。如果T是一个类型变量，则语句
//    T[] arr =new T[10];//右边是非法的
//    是非法的。T将由它的限界代替，这很可能是object T,
//    //于是（由类型擦除产生的）对工门的类型转换将无法进行，
//    //因为object[] IS-NOT-A T[]。由于我们不能创建泛型对象的数组，
//    //因此一般说来我们必须创建一个擦除类型的数组，然后使用类型转换。
//    //这种类型转换将产生一个关于未检验的类型转换的编译警告。
//    private AnyType[] anyTypes= new AnyType[10];


    // Public methods
    public AnyType read() {
        return storedValue;
    }
    public void write( AnyType x ) { storedValue = x; }
}
