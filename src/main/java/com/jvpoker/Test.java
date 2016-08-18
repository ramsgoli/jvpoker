package com.jvpoker;

import com.jvpoker.Card;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
	
	ArrayList<Integer> dk = new ArrayList<Integer>();
	int maxnum = 5;
	dk.add(4);
	dk.add(4);
	dk.add(5);
	dk.add(3);

	for (int num : dk) {
		if (num < maxnum) {
			dk.remove(num);
		}
	}	
	}
}
