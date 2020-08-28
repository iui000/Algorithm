package GinkgoStack.P22_AnalysisInJava3Third.C6_PriorityQueue_Heap;// com.GinkgoStack.C6_PriorityQueue_Heap.BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws com.GinkgoStack.UnderflowException as appropriate


import GinkgoStack.P22_AnalysisInJava3Third.UnderflowException;

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo dijstra.
 * @author Mark Allen Weiss
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }
    
    /**
     * Construct the binary heap given an array of items.
     */
    public BinaryHeap( AnyType [ ] items )
    {
            currentSize = items.length;
            array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

            int i = 1;
            for( AnyType item : items )
                array[ i++ ] = item;
            buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        /**
         * 如果容量达到数组最大值，就扩容两倍
         */
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        /**
         * Percolate up 上滤操作（空穴往上走）
         */
        int hole = ++currentSize;//设置空穴位置，也就是完全树的最后一个节点
        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];//只要比父节点小，父节点就往下落

        array[ hole ] = x;//等上滤过程结束，就找到该元素插入的地方了
    }


    /**
     * 扩容
     *
     * 构建一个新的数组，然后把原来的值复制进去
     * @param newSize
     */
    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }
    
    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an com.GinkgoStack.UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an com.GinkgoStack.UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];//把最后一个元素放入根节点空穴
        percolateDown( 1 );//开始下滤

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array

    /**
     * Internal dijstra to percolate down in the heap.
     * @param hole the index at which the percolate begins.下滤的起始位置
     *             空穴往下走
     */
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        //防止节点总数为偶数时出错
        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;//左儿子
            //要判断是否到底层了，如果右儿子小于左儿子，那就取右儿子，总之，要取那个更小的儿子
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            //如果孩子节点更小，那么空穴往下落，孩子往上走
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        //最后将该节点放置在空穴的地方
        array[ hole ] = tmp;
    }

    // Test program
    public static void main( String [ ] args )
    {
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );

        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }
}
