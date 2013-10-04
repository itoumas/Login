package Action;

import static org.junit.Assert.*;
import org.junit.Test;

public class InsertActionTest extends InsertAction {

	@Test
	public void testEdit() throws Exception {

		InsertAction insertAction = new InsertAction();
		assertEquals("データの追加をする(すでに同じデータがある)", "追加できませんでした", insertAction.edit("", "itou", "itou", "itou"));
		assertEquals("データの追加をする(必須項目が未入力)", "追加できませんでした", insertAction.edit("", "", "itou2", "itou2"));
	}
}
