package ;

public class HuffmanTree {
	public HuffmanNode root;
	
	public HuffmanTree(HuffmanNode huff){
		this.root = huff;
	}

	
	public void printLegend(){
		printLegend(root,"");
	}
	
	public void printLegend(HuffmanNode t, String s){
		if (t.letter.length()>1){
			printLegend(t.left,s + "0");
			printLegend(t.right, s + "1");
		}else if (t.letter.length()==1){
			System.out.println(t.letter+"="+s+" ");
		}
	}
	
	public static BinaryHeap legendToHeap(String legend){
		BinaryHeap<HuffmanNode> q = new BinaryHeap<HuffmanNode>(legend.length());
		String[] res = legend.split(" ");
		for (int i = 0; i< res.length;i++){
			if (res[i].matches("[0-9]*")){
				HuffmanNode nn = new HuffmanNode(res[i-1],Double.parseDouble(res[i]));
				q.insert(nn);
			}
		}
		return q;
		
	}
	
	public static HuffmanTree createFromHeap(BinaryHeap b){
		while (b.getSize()>1){
			HuffmanNode x = (HuffmanNode) b.deleteMin();
			HuffmanNode y = (HuffmanNode) b.deleteMin();
			HuffmanNode n = new HuffmanNode(x,y);
			b.insert(n);
		}
		HuffmanTree result = new HuffmanTree((HuffmanNode) b.deleteMin());
		return result;
	}

	public static void main(String[] args){
		String a = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
		BinaryHeap bheap = legendToHeap(a);
		bheap.printHeap();
		HuffmanTree htree = createFromHeap(bheap);
		htree.printLegend();
	}
}
