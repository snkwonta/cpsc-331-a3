import java.util.*;

public class truthtable {

	public static void main(String[] args) {
		// Section S3
		// Precondition: Dictionary
		// Postcondition: Map for dictionary
		
		Map<String, String> map = new HashMap<String, String>();																													
		Scanner input = new Scanner(System.in);																		// implement scanner class
		System.out.println("Input a logical expression: ");															// ask for user input
		String table = input.nextLine();																			// save user input
		ArrayList<String> var = new ArrayList<String>();															// instantiate string array list var
		ArrayList<String> var1 = new ArrayList<String>();															// instantiate string array list var1
		ArrayList<String> var5 = new ArrayList<String>();															// instantiate string array list var5
		
		// Section S1
		// Precondition: User input
		// Postcondition: Set of independent variables
		
		int i = 0;																									// i = 0
		while(i < table.length()){																					// check if i i sless than table length
			if(table.charAt(i) != '*' && 																			// If statement to check if the characters are not letters
					table.charAt(i) != '+' && 
					table.charAt(i) != '-' && 
					table.charAt(i) != '(' && 
					table.charAt(i) != ')' && 
					table.charAt(i) != ' '){
				var.add(Character.toString(table.charAt(i)));														// If they are not letters, add them to the ArrayList "var"
			}
			i++;																									// increment i
		}
		
		int a = 0;																									// variable a  0;
		while(a < var.size()){																						// check if a is less than size
			boolean res = searchVariables(var.get(a), var1);														// if so, call searchVariables method
			if(!res){
				var1.add(var.get(a));																				// add var.get(a) to var1
			}
			a++;																									// increment a to avoid infinite loop
		}
		System.out.println("Set of independent variables: " + var1);												// print out var1 which holds the set of independent variables
		
		
		int numOfRows = (int) Math.pow(2, var1.size());																// calculate the number of rows for the truth table
		
		//Section S2
		// Precondition: User input
		// Postcondition: Print out logical subexpressions
		
		Stack<Integer> stack = new Stack<Integer>();																// instantiate integer variable
		Stack<String> stack2 = new Stack<String>();																	// instantiate string stack
		int index = 0;																								// index = 0
		while(index < table.length()){																				// check if index is less than table length
			char ch = table.charAt(index);																			// set character ch to loop through user input
			if(ch == '('){																							// if the character is an opening bracket
				stack.push(index);																					// push the index to the stack
			} else if (ch == ')'){																					// if the character is a closing bracket
				stack.push(index);																					// push the index to the stack
				stack.pop();																						// pop elements in the stack
				int start = stack.pop() + 1;																		// initialize where to start popping from
				stack2.push(table.substring(start, index));															// push everything popped from where we start until the index which is from the opening to closing brackets
			}
			index++;																								// increment index
		}
		int b = 0;																									// b = 0;
		int num = 1;																								// num = 1;
		ArrayList<String> var4 = new ArrayList<String>();															// instantiate string array list
		while(b < stack2.size()){																					// check if b is less than the stack 
			String key = stack2.get(b);																				// set the key to loop through stack2
			if(!searchVariables(key, var4)){																		// call searchVariables to check for duplicate subexpressions
				String express = "LE";																				//
				express += num;																						//
				var4.add(key);																						// add the key to var4
				map.put(express, key);																				//
				System.out.println("Set of logical subexpressions: "  + "LE" + num + "=" + map.get("LE" + num));	//
				num++;																								// increment num
			}else{
				b++;																								// increment b then continue
				continue;																							
			}
		}
		
		//Section S4
		//Precondition: Arrays of logical expressions and independent variables
		//Postcondition: 2D array
		var5.addAll(var1);																							// add var1 to var5
		var5.addAll(var4);																							// add var4 to var5
		String[][] truthTable = new String[numOfRows + 1][var5.size()];												// set up 2d array for the truth table
		
		int count = 0;																								// count = 0
		int count1 = 1;																								// count1 = 1
		while(count < var5.size()){																					// check if count is less than var5.size
			if(count < var1.size()){																				// check if count is less than var1.size
				truthTable[0][count] = var1.get(count);																//
			} else {
				truthTable[0][count] = ("LE" + count1);																//
				count1++;																							// increment count1
			}
			count++;																								// increment count
		}
		
		int change = 1;																								// change = 1
		while(change < numOfRows + 1){																				// check if change is less than numOfRows+1
			int switches = (int) (numOfRows / Math.pow(2, change));													// switches = numOfRows / 2^change
			int changes = 0;																						// changes = 0
			while(changes < var1.size()){
				truthTable[change][changes] = ("T");
				changes++;																							// increment changes
			} 
			if(change == numOfRows + 1){
				System.out.println("number" + changes);
				int changes1 = 0;
				while(changes1 == switches){
					truthTable[numOfRows][change] = ("F");	
					changes1++;																						// changes
				}
			}
			change++;																								// increment change
		}

		System.out.println(Arrays.deepToString(truthTable).replace("], ", "]\n"));

	}


	public static boolean searchVariables(String string, ArrayList<String> var) {									// search algorithm
		boolean res = false;																						// set boolean to false
		int x = 0;																									// x = 0
		while(x < var.size() && !var.get(x).equalsIgnoreCase(string)){
			x++;																									// increment x
		}
			if(x < var.size()){																						// if x is less than the size of var
				res = true;																							// set res to true
			} else{
				res = false;																						// set res to false
			}
	
		return res;																									// return the value of res

	}

}


