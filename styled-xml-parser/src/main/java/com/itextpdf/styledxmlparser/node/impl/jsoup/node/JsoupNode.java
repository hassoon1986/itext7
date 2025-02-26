/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: Bruno Lowagie, Paulo Soares, et al.
    
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS
    
    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/
    
    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.
    
    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.
    
    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.
    
    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.itextpdf.styledxmlparser.node.impl.jsoup.node;

import com.itextpdf.styledxmlparser.LogMessageConstant;
import com.itextpdf.styledxmlparser.jsoup.nodes.Node;
import com.itextpdf.styledxmlparser.node.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link INode} interface; wrapper for the JSoup {@link Node} class.
 */
public class JsoupNode implements INode {

    /** The JSoup node instance. */
    private Node node;

    /** The child nodes. */
    private List<INode> childNodes = new ArrayList<>();
    
    /** The parent node. */
    INode parentNode;

    /**
     * Creates a new {@link JsoupNode} instance.
     *
     * @param node the node
     */
    public JsoupNode(Node node) {
        this.node = node;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.styledxmlparser.html.node.INode#childNodes()
     */
    @Override
    public List<INode> childNodes() {
        return Collections.unmodifiableList(childNodes);
    }

    /* (non-Javadoc)
     * @see com.itextpdf.styledxmlparser.html.node.INode#addChild(com.itextpdf.styledxmlparser.html.node.INode)
     */
    @Override
    public void addChild(INode node) {
        if (node instanceof JsoupNode) {
            childNodes.add(node);
            ((JsoupNode) node).parentNode = this;
        } else {
            Logger logger = LoggerFactory.getLogger(JsoupNode.class);
            logger.error(LogMessageConstant.ERROR_ADDING_CHILD_NODE);
        }
    }

    /* (non-Javadoc)
     * @see com.itextpdf.styledxmlparser.html.node.INode#parentNode()
     */
    @Override
    public INode parentNode() {
        return parentNode;
    }
}
