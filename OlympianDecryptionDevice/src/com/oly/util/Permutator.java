package com.oly.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Permutator<OBJ> implements Iterator<OBJ[]>{
	
	
	protected OBJ[] data;
	protected List<OBJ[]> calculated;
	protected int iteration;
	
	//Calculation information
	
	
	public Permutator(OBJ[] data) {
		this.data = data;
		iteration = 0;
		calculated = new ArrayList<OBJ[]>();
		gen_permutate(Arrays.copyOf(data,data.length),0);
		Logger.instance.LOG("Permutator Created {" + calculated.size() + " / " + getArrangementCount());
	}
	
	private void gen_permutate(OBJ[] arr,int k) {
		//k detemines the location swapping happens to
		for(int i = k; i < (data.length - 1); i++) { //NB WAS 1
			//Apply permutations
			
			//If k = data.length
			if(k == data.length) {//ADD
				calculated.add(Arrays.copyOf(arr, arr.length));
			}else {//SWAP AND RECURR
				//SWAP k AND i
				OBJ ival = arr[i];
				arr[i] = arr[k];
				arr[k] = ival;
				
				//CALL SPIN OFF FUNCTIONALITY
				gen_permutate(arr, k - 1);
			}
			
		}
		//BECAUSE I AM A MORON
		//if(k == data.length) {
		//	calculated.add(Arrays.copyOf(arr, arr.length));
		//}
	}
	
	
	
	
	@Override
	public boolean hasNext() {
		return iteration < getArrangementCount();
	}

	@Override
	public OBJ[] next() {
		iteration++;
		return calculated.get(iteration - 1);
	}
	
	
	public int getArrangementCount() {
		int output = data.length;
		for(int i = data.length; i > 1; i--) {
			output = output * data.length;
		}
		return output;
	}
	
	public OBJ[] getNthPermutation(int n) {
		return calculated.get(n);
	}
	
	
	
}
