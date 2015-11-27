/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */




/** 
   @author Andrew McCallum <a href="mailto:mccallum@cs.umass.edu">mccallum@cs.umass.edu</a>
 */

package com.bene.uni_leipzig.informatik.nlp.asse.classification.mallet;

import cc.mallet.types.Instance;
import java.io.*;
import java.util.Iterator;
import java.util.regex.*;
import java.net.URI;
import java.net.URISyntaxException;

import cc.mallet.pipe.Pipe;
import cc.mallet.types.*;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import cc.mallet.types.Instance;

public class ArrayIterator implements Iterator<Instance>
{
	Iterator subIterator;
        Pattern lineRegex;
	int uriGroup, targetGroup, dataGroup;
	String currentLine;
	int index;
	
        public ArrayIterator (List data, String lineRegex, int dataGroup, int targetGroup, int uriGroup)
	{
		this.subIterator = data.iterator();
		this.lineRegex = Pattern.compile(lineRegex);
		this.targetGroup = targetGroup;
		this.dataGroup = dataGroup;
		this.uriGroup = uriGroup;
		if (dataGroup <= 0)
			throw new IllegalStateException ("You must extract a data field.");
		if(subIterator.hasNext()) {
			this.currentLine = (String)subIterator.next();
		} 
	}
        
        public Instance next ()
	{
		String uriStr = null;
		String data = null;
		String target = null;
		Matcher matcher = lineRegex.matcher(currentLine);
		if (matcher.find()) {
			if (uriGroup > 0)
				uriStr = matcher.group(uriGroup);
			if (targetGroup > 0)
				target = matcher.group(targetGroup);
			if (dataGroup > 0)
				data = matcher.group(dataGroup);
		} else {
			throw new IllegalStateException ("Line #"+currentLine+" does not match regex:\n" +
											 currentLine);
		}

		String uri;
		if (uriStr == null) {
			uri = "csvline:"+currentLine;
		} else {
			uri = uriStr;
		}
		assert (data != null);
		Instance carrier = new Instance (data, target, uri, null);
		if(subIterator.hasNext()) {
			this.currentLine = (String)subIterator.next();
		} else {
                    this.currentLine = null;
                }
		return carrier;
	}

	public boolean hasNext ()	{	return currentLine != null;	}

	public void remove() { 
            throw new IllegalStateException ("This Iterator<Instance> does not support remove().");
            //subIterator.remove(); 
        }
	
}

