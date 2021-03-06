
import java.util.Scanner;
import java.util.Stack;

/*
 * This is a code snippet to convert a simple Infix Expression to a Postfix one.
 * NOTE : It would not work for expressions containing paranthesis. It only takes into 
 * account operators for the following operations:
 * 1. Multiplication
 * 2. Division
 * 3. Modulus
 * 4. Addition
 * 5. Subtraction
 * 
 * The precedence of the operations is shown below. Lower level operations have a higher 
 * precedence, so here, operations in Level 1 have higher precedence than those mentioned
 * in Level 2. 
 * Operations mentioned in the same level have equal precedence.
 *  
 * Level 1. Multiplication, Division, Modulus
 * Level 2. Addition, Subtraction  
 */
public class Infix2Postfix {
	
	private Stack<String> stack;
	private String infixExp;
	private String postfixExp = "";
	
	public String returnS;
	public Infix2Postfix(String exp){
		
		String str = "";
		infixExp = exp;
		stack = new Stack<String>();
		
		for (int i=0;i<infixExp.length();i++){
			/* 
			 * If the character is a letter or a digit we append it to the postfix 
			 * expression directly. 
			 */
			str = infixExp.substring(i,i+1);
			if(str.matches("[a-zA-Z]|\\d"))
				postfixExp += str;
			else if (isOperator(str)){
				/*
				 * If the stack is empty we directly push the current char into it.
				 */
				if (stack.isEmpty()){
					stack.push(str);
				}
				else{
					/*
					 * If the current character is an operator, we need to check the stack 
					 * status then, if the stack top contains an operator with lower
					 * precedence, we push the current character in the stack else we pop
					 * the character from the stack and add it to the postfix string. This 
					 * continues till we either find an operator with lower precedence in the 
					 * stack or we find the stack to be empty.
					 */
					String stackTop = stack.peek();
					while (getPrecedence(stackTop,str).equals(stackTop)&& !(stack.isEmpty())){
						postfixExp += stack.pop();
						if (!(stack.isEmpty()))
							stackTop = stack.peek();
					}
					stack.push(str);
				}
			}
		}
		// In the end just append all the operators from the stack to the postfix expression.
		while(!(stack.isEmpty()))
			postfixExp += stack.pop();
		// Print out the postfix expression
		//System.out.println("The postfix form of the expression you entered is: " + postfixExp);
		returnS=postfixExp;
	}
	
	/*
	 * Returns true if 'ch' is an operator, false o/w
	 */ 
	private boolean isOperator(String ch){
		
		String operators = "*/%+-";
		if (operators.indexOf(ch) != -1)
			return true;
		else
			return false;
	}
	
	/*
	 * Returns the operator with higher precedence among 'op1' & 'op2', if they have equal
	 * precedence, the first operator in the argument list (op1) is returned.
	 */ 
	private String getPrecedence(String op1, String op2){
		
		String multiplicativeOps = "*/%";
		String additiveOps = "+-";
		
		if ((multiplicativeOps.indexOf(op1) != -1) && (additiveOps.indexOf(op2) != -1))
			return op1;
		else if ((multiplicativeOps.indexOf(op2) != -1) && (additiveOps.indexOf(op1) != -1))
			return op2;
		else if((multiplicativeOps.indexOf(op1) != -1) && (multiplicativeOps.indexOf(op2) != -1))
			return op1;
		else 
			return op1;
	}		
	
	public static void main(String[] args){
		
		//System.out.println("Enter an expression in the Infix form:");
		//Scanner scanner = new Scanner(System.in);
		
		String expression ="(b * b ) * ( f * g )";// scanner.nextLine();
		System.out.println("infix : "+expression);
		new Infix2Postfix(expression);
	}
}