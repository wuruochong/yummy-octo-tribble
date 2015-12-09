/*
WARS - Ruochong Wu and Shaik Abiden
APCS1 PD10
HW44 -- This or That or Fourteen Other Things
2015-08-12
*/
public class Hexadecimal {

    private final static String HEXDIGITS = "0123456789ABCDEF"; 
    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	_decNum = n;
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_binNum = s;
	_decNum = binToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	String res = "";
	while (n != 0){
	    res = n%2 + res;
	    n = n/2;
	}
	return res;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToHexR( int n ) { 
	if (n == 0){
	    return "";
	}
	else{
	    return decToHexR(n/16) + HEXDIGITS.substring((n%16),(n%16) + 1);
	}
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int res = 0;
	for (int count = 0 ; count < s.length() ; count++){
	    res += Integer.parseInt(s.substring(count,count+1));
	    res *= 2;
	}
	return res/2;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int hexToDecR( String s ) { 
	if (s.length() == 0){
	    return 0;
	}
	else {
	    return (HEXDIGITS.indexOf(s.substring(0,1)) * (int)Math.pow(16,s.length()-1)) + hexToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	return ((this.toString()).equals(other.toString()));
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	int otherVal = binToDec(other.toString());
	if (!(other instanceof Hexadecimal)){
	    throw new ClassCastException("The input isn't a Hexadecimal");
	}
	if (this._decNum == otherVal){
	    return 0;
	}
	else if(this._decNum > otherVal){
	    return 1;
	}
	else {
	    return -1;
	}
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal(5);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	System.out.println( decToHexR(7));
	System.out.println( decToHexR(6));
	System.out.println( decToHexR(17));
	System.out.println( decToHexR(16));
	System.out.println( decToHexR(15));
	System.out.println( decToHexR(20));
	System.out.println( decToHexR(32));
	System.out.println( decToHexR(33));
	System.out.println( "Testing hexToDecR()");
	System.out.println( hexToDecR("0"));
	System.out.println( hexToDecR("5"));
	System.out.println( hexToDecR("F"));
	System.out.println( hexToDecR("A"));
	System.out.println( hexToDecR("B"));
	System.out.println( hexToDecR("1F"));
	System.out.println( hexToDecR("7G"));
	System.out.println( hexToDecR("2E"));
	System.out.println( hexToDecR("111"));
	System.out.println( hexToDecR("2F"));
    }//end main()

} //end class
