/**
 * File: MyTextEditor.java
 *
 * This is a simple text editor built for lines of text.  
 * Each line of text is to be viewed as a 
 * separate string.  In addition, this editor has a cursor associated 
 * with some line in the text.  Initially, the cursor is set to the the 
 * line -1 just before the first line in the text.  The methods of the 
 * text editor are designed to update and print the text file using the 
 * cursor.
 *
 * @author Kevin Liu 
 * @version 1.0, 03/12/2014
 * @see BoundaryViolationException
 * @see String
 */

public class MyTextEditor {
	private ArraySequence<String> text;
	private int cursor;

	public MyTextEditor() {
		text = new ArraySequence<String>();
		cursor = -1;
	}
	
  /** 
   * Returns true if the text is completely empty (and cursor is at line -1).
   */
  public boolean isEmpty() {
		return text.isEmpty();
	}

  /** 
   * Returns the current number of lines of text. 
   */
  public int size() {
		return text.size();
	}

  /** 
   * Returns true if the cursor is at the last line in the text or the text 
   * is empty.
   */
  public boolean isCursorAtLastLine() {
		return cursor == text.indexOf(text.last());	
	}

  /** 
   * Sets the cursor to be the text line after its current position.
   */
  public void cursorDown() throws BoundaryViolationException {
		text.checkBoundary(cursor+1, text.size());
		cursor = text.indexOf(text.next(text.atIndex(cursor)));
	}

  /** 
   * Sets the cursor to be the text line before its current position.
   */
  public void cursorUp() throws BoundaryViolationException {
		text.checkBoundary(cursor-1, size());
		cursor = text.indexOf(text.prev(text.atIndex(cursor)));
	}

  /** 
   * Sets the cursor to be the line ranked i (the first line is line 0).
   */
  public void moveCursorToLine(int i) throws BoundaryViolationException {
		text.checkBoundary(i, text.size());
		cursor = i;
	}

}