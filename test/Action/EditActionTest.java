package Action;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditActionTest {

	@Test
	public void testEdit() throws Exception {

		EditAction editAction = new EditAction();
		assertEquals("", editAction.edit("", "", "", ""));
	}
}
