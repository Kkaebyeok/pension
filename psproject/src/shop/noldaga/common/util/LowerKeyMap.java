package shop.noldaga.common.util;

import java.util.HashMap;

public class LowerKeyMap extends HashMap<Object, Object>{
	@Override
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		return super.put(key.toString().toLowerCase(), value);
	}
}
