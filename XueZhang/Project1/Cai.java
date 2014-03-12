/** Tester driver
 * file name: Cai.java
 * @author Yisheng Cai
 * @version 1.0.0 03/24/13
 */

public class Cai {
  /** Prints all elements of a given array list. */
	public static void main(String args[]){
		//creatign an array, instantiating MyTextEditor
		MyTextEditor A = new MyTextEditor();

		A.insertAfterCursor("Narnia...where the woods are thick and cool, where Talking Beasts are called to");
		A.insertAfterCursor("life, the land beyond the wardrobe, the secret country known only to Peter,");
		A.insertAfterCursor("Susan, Edmund, and Lucy. Narnia...where horses talk and hermits like company,");
		A.insertAfterCursor("where evil men turn into donkeys, where boys go into battle. ");
		A.insertAfterCursor("Narnia...the land between the lamp-post and the castle of Cair Paravel,");
		A.insertAfterCursor("where animals talk, where magical things happen,the world of wicked dragons ");
		A.insertAfterCursor("and magic spells, where the very best is brought out of even the worst people,");
		A.insertAfterCursor("where anything can happen(and most oftan does). ");
		A.insertAfterCursor("Narnia...where owls are wise, where some of the giants like to");
		A.insertAfterCursor("snack on humans (and, if carefully cooked, on Marsh-wiggles, too), ");
		A.insertAfterCursor("where a prince is put under an evil spell. ");
		A.insertAfterCursor("Narnia...where dwarfs are loyal and tough and strong-or are they?");
		System.out.println("*******************initial.txt...************************");
		A.print();

		//Testing isCursorAtLastLine()
		if (A.isCursorAtLastLine())
			System.out.println("The cursor is at the last line.");
		System.out.println("");

		System.out.println("Moving the cursor to first line...");
		A.moveCursorToLine(0);

		//Testing isEmpty()
		if (A.isEmpty())
			System.out.println("The text is empty.");
		else
			System.out.println("The text is not empty. ");
		

		//Testing size()
		System.out.println("This text has "+A.size()+" lines.");
		//Testing cursorLineNum()
		System.out.println("cursor is at line "+A.cursorLineNum()+".");
		
		//Testing cursorDown()
		A.cursorDown();
		A.cursorDown();
		A.cursorDown();

		//Testing insertAfterCursor(int i)
		A.insertAfterCursor("");

		//Testing moveCursorToLine()
		A.moveCursorToLine(10);
		System.out.println("cursor is at line "+A.cursorLineNum()+".");
		A.insertBeforeCursor("");
	
		//Testing cursorUp()
		A.cursorUp();
		System.out.println("cursor is at line "+A.cursorLineNum()+".");


		A.moveCursorToLine(14);
		if (A.isCursorAtLastLine())
			System.out.println("The cursor is at the last line.");

		//Testing insertBeforeCursor(int i)
		A.insertBeforeCursor("");
		System.out.println("cursor is at line "+A.cursorLineNum()+".");
		
		
		//Testing removeAtCursor()
		A.removeAtCursor();
		System.out.println("cursor is at line "+A.cursorLineNum()+".");

		//Testing replaceAtCursor(String s)
		A.replaceAtCursor("haha");
		A.removeAtCursor();
		A.insertAfterCursor("");
		A.insertAfterCursor("Narnia... where dwarfs are loyal and tough and strong-or are they?");
		System.out.println("");
		System.out.println("*****************converting to middle.txt...*******************");
		A.print();
		
		//Final.txt
		A.moveCursorToLine(2);
		A.insertAfterCursor("");
		A.moveCursorToLine(2);
		A.replaceAtCursor("Susan, Edmund, and Lucy. ");
		
		A.insertAfterCursor("Narnia... where horses talk and hermits like company,");
		A.moveCursorToLine(16);
		A.replaceAtCursor("Narnia... where dwarfs are loyal and tough and strong---or are they really?");
		System.out.println("");
		System.out.println("********************converting to final.txt...*************************");
		A.print();

		
		

		
		
		
	}
}
