import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DemoTest {
    public static void main(String[] args) {
        int[] array = new int[]{1, 4, 6, 7, 7, 2, 3, 3, 0, 9, 10, 6, 14, 15, 10, 22, 3, 5};

        /**
         * 一、int[ ] 转成 Integer[ ], List< Intege r>, List< String >
         */
        //int[ ] 转成 Integer[ ]
        Integer[] arrayInteger = Arrays.stream(array).boxed().toArray(Integer[]::new);
        //int[ ] 转成 List< Integer >
        List<Integer>  arrayIntegerList = Arrays.stream(array).boxed().collect(Collectors.toList());
        //int[ ] 转成 List< String >
        List<String> arrayStringList = Arrays.stream(array).boxed().map(String::valueOf).collect(Collectors.toList());

        /**
         * 二、Integer[ ] 转成 int[ ] , List< Integer>, List< String >
         */
        //Integer[ ] 转成 int[ ]
        int[] int_Integer = Arrays.stream(arrayInteger).mapToInt(Integer::intValue).toArray();
        //Integer[] 转成 List< Integer > 该list不能add, remove操作
        List<Integer> integerList_integer = Arrays.asList(arrayInteger);
        //Integer[] 转成 List< Integer > 该list支持所有操作
        List<Integer> integerList_newInteger = new ArrayList<>(Arrays.asList(arrayInteger));
        //Integer[] 转成 List< String >
        List<String> stringList_integer = Arrays.stream(arrayInteger).map(String::valueOf).collect(Collectors.toList());

        /**
         * 三、List< Integer > 转成 int[ ], Integer[ ], List< String >
         */
        //List< Integer > 转成 int[ ]....................转成流 再转成Integer[] 再转int
        int[] array_listInteger = arrayIntegerList.stream().mapToInt(Integer::intValue).toArray();
        //List< Integer > 转成 Integer[ ]
        Integer[] integer_integerList = arrayIntegerList.toArray(new Integer[array.length]);
        //List< Integer > 转成 List< String >
        List<String> stringList_IntegerList = arrayIntegerList.stream().map(String::valueOf).collect(Collectors.toList());

        /**
         * 四、List< String > 转成 int[ ], Integer[ ], List< List >
         */
        //List< String > 转成 int[ ]
        int[] int_stringList = arrayStringList.stream().mapToInt(Integer::valueOf).toArray();
        //List< String > 转成 Integer[ ]
        Integer[] integer_stringList = arrayStringList.stream().mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
        //List< String > 转成 List< Integer >
        List<Integer> integerList_stringList = arrayStringList.stream().map(Integer::valueOf).collect(Collectors.toList());
        String res = String.join(",", arrayStringList);
    }
}
