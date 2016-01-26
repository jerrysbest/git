package erp.ws.sbo.client.swing.util.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public  class ProcessHierarchy {
	
	  public ProcessHierarchy()
	  {
		  
	  }
	  public  DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
		    DefaultMutableTreeNode node =
		      new DefaultMutableTreeNode(hierarchy[0]);
		    DefaultMutableTreeNode child;
		    for(int i=1; i<hierarchy.length; i++) {
		      Object nodeSpecifier = hierarchy[i];
		      if (nodeSpecifier instanceof Object[])  // Ie node with children
		        child = processHierarchy((Object[])nodeSpecifier);
		      else
		        child = new DefaultMutableTreeNode(nodeSpecifier); // Ie Leaf
		      node.add(child);
		    }
		    return(node);
		  }

}
