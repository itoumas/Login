package Action;

import static org.junit.Assert.*;
import org.junit.Test;

public class DeleteActionTest extends DeleteAction {

	@Test
	public void testEdit() throws Exception {

		DeleteAction deleteAction = new DeleteAction();

		assertEquals("データの削除をする(IDを未指定)", "削除できませんでした", deleteAction.edit("", "", "", ""));
		assertEquals("データの削除をする(存在しないIDを指定)", "削除できませんでした", deleteAction.edit("4", "", "", ""));
	}
}
