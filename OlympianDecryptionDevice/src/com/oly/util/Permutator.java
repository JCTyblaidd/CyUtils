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
		gen_permutate(Arrays.copyOf(data, data.length),0);
		Logger.instance.LOG("Permutator Created {" + calculated.size() + " / " + getArrangementCount() + "} >> " + data.length);
		//System.out.println("[PERMUTATOR] Perm Count Guess => " + getArrangementCount() + "//" + data.length);
	}
	
	private void gen_permutate(OBJ[] arr,int k) {//TODO remove debug code below and tidy
		//System.out.println("[PERMUTATOR] Genning perms w/ array size =>" + k);
		//k detemines the location swapping happens to
		for(int i = k; i <= (data.length - 1); i++) { //NB WAS 1
			//Apply permutations
			
			//If k = data.length
			if(k == data.length - 1) {//ADD
				calculated.add(Arrays.copyOf(arr,data.length));
			}else {//SWAP AND RECURR
				//SWAP k AND i
				OBJ ival = arr[i];
				arr[i] = arr[k];
				arr[k] = ival;
				
				
				
				//CALL SPIN OFF FUNCTIONALITY
				gen_permutate(arr, k + 1);
			}
			
		}
		//BECAUSE I AM A MORON
		//if(k == data.length) {
		//	calculated.add(Arrays.copyOf(arr, arr.length));
		//}
	}
	
	
	
	
	@Override
	public boolean hasNext() {
		//return iteration < getArrangementCount();
		return iteration < calculated.size();
	}

	@Override
	public OBJ[] next() {
		iteration++;
		return calculated.get(iteration - 1);
	}
	
	
	public int getArrangementCount() {//N! permutations
		int output = 1;
		int temp = data.length;
		while(temp > 1) {
			output = output * temp;
			temp--;
		}
		return output;
	}
	
	public OBJ[] getNthPermutation(int n) {
		return calculated.get(n);
	}
	
	
	
}
