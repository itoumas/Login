package Action;

import static org.junit.Assert.*;
import org.junit.Test;

public class UpdateActionTest extends UpdateAction {

	@Test
	public void test() throws Exception {

		UpdateAction updateAction = new UpdateAction();

		assertEquals("データの更新する", "完了！！", updateAction.edit("3", "systena", "systena", "systena"));
		assertEquals("データの更新する(存在しないIDの更新)", "更新できませんでした", updateAction.edit("4", "systena", "systena", "systena"));
	}
}
