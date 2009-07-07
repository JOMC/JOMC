// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions
 *   are met:
 *
 *     o Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     o Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE JOMC PROJECT AND CONTRIBUTORS "AS IS"
 *   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE JOMC PROJECT OR
 *   CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *   OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *   WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *   OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *   ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $Id$
 *
 */
// SECTION-END
package org.jomc.sequences.util;

import java.beans.ExceptionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.table.AbstractTableModel;
import org.jomc.ObjectManagementException;
import org.jomc.sequences.ConcurrentModificationException;
import org.jomc.sequences.SequenceVetoException;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequencesSystemException;

// SECTION-START[Implementation Comment]
/**
 * Swing {@code TableModel} Java Bean for displaying and editing a system's {@code SequenceDirectory}.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.util.SequencesTableModel} {@code 1.0}<blockquote>
 * Object applies to Context scope.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #isNameColumnEditableByDefault nameColumnEditableByDefault}"<blockquote>
 * Property of type {@code java.lang.Boolean} with value "true".</blockquote></li>
 * <li>"{@link #isMinimumColumnEditableByDefault minimumColumnEditableByDefault}"<blockquote>
 * Property of type {@code java.lang.Boolean} with value "true".</blockquote></li>
 * <li>"{@link #isMaximumColumnEditableByDefault maximumColumnEditableByDefault}"<blockquote>
 * Property of type {@code java.lang.Boolean} with value "true".</blockquote></li>
 * <li>"{@link #isIncrementColumnEditableByDefault incrementColumnEditableByDefault}"<blockquote>
 * Property of type {@code java.lang.Boolean} with value "true".</blockquote></li>
 * <li>"{@link #isValueColumnEditableByDefault valueColumnEditableByDefault}"<blockquote>
 * Property of type {@code java.lang.Boolean} with value "true".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getSequenceDirectory SequenceDirectory}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceDirectory} at specification level 1.0 applying to Singleton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getExceptionListener ExceptionListener}"<blockquote>
 * Dependency on {@code java.beans.ExceptionListener} at specification level 1.4 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getNameColumnTitleMessage nameColumnTitle}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Name</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Name</pre></td></tr>
 * </table>
 * </li>
 * <li>"{@link #getMinimumColumnTitleMessage minimumColumnTitle}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Minimum</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Minimum</pre></td></tr>
 * </table>
 * </li>
 * <li>"{@link #getMaximumColumnTitleMessage maximumColumnTitle}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Maximum</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Maximum</pre></td></tr>
 * </table>
 * </li>
 * <li>"{@link #getIncrementColumnTitleMessage incrementColumnTitle}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Increment</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Inkrement</pre></td></tr>
 * </table>
 * </li>
 * <li>"{@link #getValueColumnTitleMessage valueColumnTitle}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Value</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Wert</pre></td></tr>
 * </table>
 * </li>
 * <li>"{@link #getIllegalColumnIndexMessage illegalColumnIndex}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal column index {0}. {1}</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ungültiger Spalten-Index {0}. {1}</pre></td></tr>
 * </table>
 * </li>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class SequencesTableModel extends AbstractTableModel implements Serializable
{
    // SECTION-START[TableModel]

    public int getRowCount()
    {
        try
        {
            return this.getSequences().size();
        }
        catch ( SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }

        return 0;
    }

    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName( final int columnIndex )
    {
        try
        {
            final String columnName;

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    columnName = this.getNameColumnTitleMessage( this.getLocale() );
                    break;

                case MINIMUM_COLUMN_INDEX:
                    columnName = this.getMinimumColumnTitleMessage( this.getLocale() );
                    break;

                case MAXIMUM_COLUMN_INDEX:
                    columnName = this.getMaximumColumnTitleMessage( this.getLocale() );
                    break;

                case INCREMENT_COLUMN_INDEX:
                    columnName = this.getIncrementColumnTitleMessage( this.getLocale() );
                    break;

                case VALUE_COLUMN_INDEX:
                    columnName = this.getValueColumnTitleMessage( this.getLocale() );
                    break;

                default:
                    columnName = super.getColumnName( columnIndex );
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            return columnName;
        }
        catch ( SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }

        return "";
    }

    @Override
    public Class<?> getColumnClass( final int columnIndex )
    {
        try
        {
            final Class columnClass;

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    columnClass = String.class;
                    break;

                case MINIMUM_COLUMN_INDEX:
                case MAXIMUM_COLUMN_INDEX:
                case INCREMENT_COLUMN_INDEX:
                case VALUE_COLUMN_INDEX:
                    columnClass = BigInteger.class;
                    break;

                default:
                    columnClass = super.getColumnClass( columnIndex );
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            return columnClass;
        }
        catch ( SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }

        return Object.class;
    }

    @Override
    public boolean isCellEditable( final int rowIndex, final int columnIndex )
    {
        try
        {
            final boolean cellEditable;

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    cellEditable = this.getNameColumnEditable();
                    break;

                case MINIMUM_COLUMN_INDEX:
                    cellEditable = this.getMinimumColumnEditable();
                    break;

                case MAXIMUM_COLUMN_INDEX:
                    cellEditable = this.getMaximumColumnEditable();
                    break;

                case INCREMENT_COLUMN_INDEX:
                    cellEditable = this.getIncrementColumnEditable();
                    break;

                case VALUE_COLUMN_INDEX:
                    cellEditable = this.getValueColumnEditable();
                    break;

                default:
                    cellEditable = super.isCellEditable( rowIndex, columnIndex );
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            return cellEditable;
        }
        catch ( SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }

        return false;
    }

    public Object getValueAt( final int rowIndex, final int columnIndex )
    {
        try
        {
            final Object value;
            final Sequence sequence = this.getSequences().get( rowIndex );

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    value = sequence.getName();
                    break;

                case MINIMUM_COLUMN_INDEX:
                    value = sequence.getMinimum();
                    break;

                case MAXIMUM_COLUMN_INDEX:
                    value = sequence.getMaximum();
                    break;

                case INCREMENT_COLUMN_INDEX:
                    value = sequence.getIncrement();
                    break;

                case VALUE_COLUMN_INDEX:
                    value = sequence.getValue();
                    break;

                default:
                    value = null;
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            return value;
        }
        catch ( SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }
        catch ( IndexOutOfBoundsException e )
        {
            this.fireExceptionThrown( e );
        }

        return null;
    }

    @Override
    public synchronized void setValueAt( final Object aValue, final int rowIndex, final int columnIndex )
    {
        try
        {
            final Sequence sequence = this.getSequences().get( rowIndex );
            final String name = sequence.getName();
            final long revision = sequence.getRevision();

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    sequence.setName( aValue.toString() );
                    break;

                case MINIMUM_COLUMN_INDEX:
                    sequence.setMinimum( (Long) aValue );
                    break;

                case MAXIMUM_COLUMN_INDEX:
                    sequence.setMaximum( (Long) aValue );
                    break;

                case INCREMENT_COLUMN_INDEX:
                    sequence.setIncrement( (Long) aValue );
                    break;

                case VALUE_COLUMN_INDEX:
                    sequence.setValue( (Long) aValue );
                    break;

                default:
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            this.getSequenceDirectory().editSequence( name, revision, sequence );
            this.fireTableRowsUpdated( rowIndex, rowIndex );
        }
        catch ( SequenceNotFoundException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
        catch ( ConcurrentModificationException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
        catch ( SequenceVetoException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
        catch ( SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
        catch ( IndexOutOfBoundsException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
    }

    // SECTION-END
    // SECTION-START[SequencesTableModel]
    /** Number of table columns. */
    public static final int COLUMN_COUNT = 5;

    /** Index of the column displaying a sequence's name. */
    public static final int NAME_COLUMN_INDEX = 0;

    /** Index of the column displaying a sequence's minimum value. */
    public static final int MINIMUM_COLUMN_INDEX = 1;

    /** Index of the column displaying a sequence's maximum value. */
    public static final int MAXIMUM_COLUMN_INDEX = 2;

    /** Index of the column displaying a sequence's increment value. */
    public static final int INCREMENT_COLUMN_INDEX = 3;

    /** Index of the column displaying a sequence's value. */
    public static final int VALUE_COLUMN_INDEX = 4;

    /** Name of property {@code nameColumnEditable}. */
    public static final String NAME_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.NAME_COLUMN_EDITABLE";

    /** Name of property {@code minimumColumnEditable}. */
    public static final String MINIMUM_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.MINIMUM_COLUMN_EDITABLE";

    /** Name of property {@code maximumColumnEditable}. */
    public static final String MAXIMUM_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.MAXIMUM_COLUMN_EDITABLE";

    /** Name of property {@code incrementColumnEditable}. */
    public static final String INCREMENT_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.INCREMENT_COLUMN_EDITABLE";

    /** Name of property {@code valueColumnEditable}. */
    public static final String VALUE_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.VALUE_COLUMN_EDITABLE";

    /** Name of property {@code sequenceFilter}. */
    public static final String SEQUENCE_FILTER =
        "org.jomc.sequences.util.SequencesTableModel.SEQUENCE_FILTER";

    /**
     * Flag indicating that the {@code name} column is editable.
     * @serial
     */
    private Boolean nameColumnEditable;

    /**
     * Flag indicating that the {@code minimum} column is editable.
     * @serial
     */
    private Boolean minimumColumnEditable;

    /**
     * Flag indicating that the {@code maximum} column is editable.
     * @serial
     */
    private Boolean maximumColumnEditable;

    /**
     * Flag indicating that the {@code increment} column is editable.
     * @serial
     */
    private Boolean incrementColumnEditable;

    /**
     * Flag indicating that the {@code value} column is editable.
     * @serial
     */
    private Boolean valueColumnEditable;

    /**
     * Entity filter.
     * @serial
     */
    private Sequence sequenceFilter;

    /** Sequences of the model. */
    private transient List<Sequence> sequences;

    /**
     * Change support.
     * @serial
     */
    private PropertyChangeSupport changeSupport = new SwingPropertyChangeSupport( this );

    /**
     * Gets the flag indicating that the {@code name} column is editable.
     *
     * @return {@code true} if the {@code name} column is editable; {@code false} if not.
     */
    public Boolean getNameColumnEditable()
    {
        if ( this.nameColumnEditable == null )
        {
            this.nameColumnEditable = this.isNameColumnEditableByDefault();
            this.changeSupport.firePropertyChange( NAME_COLUMN_EDITABLE, null, this.nameColumnEditable );
        }

        return this.nameColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code name} column is editable.
     *
     * @param value {@code true} if the {@code name} column should be editable; {@code false} if not.
     */
    public void setNameColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.nameColumnEditable;
        this.nameColumnEditable = value;
        this.changeSupport.firePropertyChange( NAME_COLUMN_EDITABLE, oldValue, this.nameColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code minimum} column is editable.
     *
     * @return {@code true} if the {@code minimum} column is editable;{@code false} if not.
     */
    public Boolean getMinimumColumnEditable()
    {
        if ( this.minimumColumnEditable == null )
        {
            this.minimumColumnEditable = this.isMinimumColumnEditableByDefault();
            this.changeSupport.firePropertyChange( MINIMUM_COLUMN_EDITABLE, null, this.minimumColumnEditable );
        }

        return this.minimumColumnEditable;
    }

    /**
     * Set the flag indicating that the {@code minimum} column is editable.
     *
     * @param value {@code true} if the {@code minimum} column should be editable; {@code false} if not.
     */
    public void setMinimumColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.minimumColumnEditable;
        this.minimumColumnEditable = value;
        this.changeSupport.firePropertyChange( MINIMUM_COLUMN_EDITABLE, oldValue, this.minimumColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code maximum} column is editable.
     *
     * @return {@code true} if the {@code maximum} column is editable; {@code false} if not.
     */
    public Boolean getMaximumColumnEditable()
    {
        if ( this.maximumColumnEditable == null )
        {
            this.maximumColumnEditable = this.isMaximumColumnEditableByDefault();
            this.changeSupport.firePropertyChange( MAXIMUM_COLUMN_EDITABLE, null, this.maximumColumnEditable );
        }

        return this.maximumColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code maximum} column is editable.
     *
     * @param value {@code true} if the {@code maximum} column should be editable; {@code false} if not.
     */
    public void setMaximumColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.maximumColumnEditable;
        this.maximumColumnEditable = value;
        this.changeSupport.firePropertyChange( MAXIMUM_COLUMN_EDITABLE, oldValue, this.maximumColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code increment} column is editable.
     *
     * @return {@code true} if the {@code increment} column is editable; {@code false} if not.
     */
    public Boolean getIncrementColumnEditable()
    {
        if ( this.incrementColumnEditable == null )
        {
            this.incrementColumnEditable = this.isIncrementColumnEditableByDefault();
            this.changeSupport.firePropertyChange( INCREMENT_COLUMN_EDITABLE, null, this.incrementColumnEditable );
        }

        return this.incrementColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code increment} column is editable.
     *
     * @param value {@code true} if the {@code increment} column should be editable; {@code false} if not.
     */
    public void setIncrementColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.incrementColumnEditable;
        this.incrementColumnEditable = value;
        this.changeSupport.firePropertyChange( INCREMENT_COLUMN_EDITABLE, oldValue, this.incrementColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code value} column is editable.
     *
     * @return {@code true} if the {@code value} column is editable; {@code false} if not.
     */
    public Boolean getValueColumnEditable()
    {
        if ( this.valueColumnEditable == null )
        {
            this.valueColumnEditable = this.isValueColumnEditableByDefault();
            this.changeSupport.firePropertyChange( VALUE_COLUMN_EDITABLE, null, this.valueColumnEditable );
        }

        return this.valueColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code value} column is editable.
     *
     * @param value {@code true} if the {@code value} column should be editable; {@code false} if not.
     */
    public void setValueColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.valueColumnEditable;
        this.valueColumnEditable = value;
        this.changeSupport.firePropertyChange( VALUE_COLUMN_EDITABLE, oldValue, this.valueColumnEditable );
    }

    /**
     * Gets the entity used for filtering sequences.
     *
     * @return Entity used for filtering sequences or {@code null}.
     */
    public Sequence getSequenceFilter()
    {
        return this.sequenceFilter;
    }

    /**
     * Sets the entity used for filtering sequences.
     *
     * @param value Entity to use for filtering sequences or {@code null}.
     */
    public void setSequenceFilter( final Sequence value )
    {
        final Sequence oldValue = this.sequenceFilter;
        this.sequenceFilter = value;
        this.sequences = null;
        this.fireTableDataChanged();
        this.changeSupport.firePropertyChange( SEQUENCE_FILTER, oldValue, this.sequenceFilter );
    }

    /**
     * Add a {@code PropertyChangeListener} to the listener list.
     * <p>The listener is registered for all properties. The same listener object may be added more than once, and will
     * be called as many times as it is added. If {@code listener} is {@code null}, no exception is thrown and no action
     * is taken.</p>
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener( final PropertyChangeListener listener )
    {
        this.changeSupport.addPropertyChangeListener( listener );
    }

    /**
     * Removes a {@code PropertyChangeListener} from the listener list.
     * <p>This removes a {@code PropertyChangeListener} that was registered for all properties. If {@code listener} was
     * added more than once, it will be notified one less time after being removed. If {@code listener} is {@code null},
     * or was never added, no exception is thrown and no action is taken.</p>
     *
     * @param listener The listener to be removed.
     */
    public void removePropertyChangeListener( final PropertyChangeListener listener )
    {
        this.changeSupport.removePropertyChangeListener( listener );
    }

    /**
     * Gets an array of all the listeners that were added to the instance.
     * <p>If some listeners have been added with a named property, then the returned array will be a mixture of
     * {@code PropertyChangeListeners} and {@code PropertyChangeListenerProxy}s. If the calling method is interested in
     * distinguishing the listeners then it must test each element to see if it's a {@code PropertyChangeListenerProxy},
     * perform the cast, and examine the parameter.</p>
     *
     * @return All of the {@code PropertyChangeListeners} added or an empty array if no listeners have been added.
     *
     * @see PropertyChangeSupport#getPropertyChangeListeners()
     */
    public PropertyChangeListener[] getPropertyChangeListeners()
    {
        return this.changeSupport.getPropertyChangeListeners();
    }

    /**
     * Add a {@code PropertyChangeListener} for a specific property.
     * <p>The listener will be invoked only when an event for that specific property occurs. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number of times it was added for
     * that property. If {@code propertyName} or {@code listener} is {@code null}, no exception is thrown and no action
     * is taken.</p>
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener( final String propertyName, final PropertyChangeListener listener )
    {
        this.changeSupport.addPropertyChangeListener( propertyName, listener );
    }

    /**
     * Removes a {@code PropertyChangeListener} for a specific property.
     * <p>If {@code listener} was added more than once to the instance for the specified property, it will be notified
     * one less time after being removed. If {@code propertyName} is {@code null}, no exception is thrown and no action
     * is taken. If {@code listener} is {@code null}, or was never added for the specified property, no exception is
     * thrown and no action is taken.</p>
     *
     * @param propertyName The name of the property that was listened on.
     * @param listener The listener to be removed.
     */
    public void removePropertyChangeListener( final String propertyName, final PropertyChangeListener listener )
    {
        this.changeSupport.removePropertyChangeListener( propertyName, listener );
    }

    /**
     * Gets an array of all the listeners which have been associated with the named property.
     *
     * @param propertyName The name of the property being listened to.
     *
     * @return All of the {@code PropertyChangeListeners} associated with the named property. If no such listeners have
     * been added, or if {@code propertyName} is {@code null}, an empty array is returned.
     */
    public PropertyChangeListener[] getPropertyChangeListeners( final String propertyName )
    {
        return this.changeSupport.getPropertyChangeListeners( propertyName );
    }

    /**
     * Gets the entities of the model.
     *
     * @return The entities of the model.
     *
     * @throws SequencesSystemException if searching entities fails.
     */
    protected List<Sequence> getSequences() throws SequencesSystemException
    {
        if ( this.sequences == null )
        {
            this.sequences = new LinkedList<Sequence>();
            this.sequences.addAll( this.getSequenceDirectory().searchSequences(
                this.getSequenceFilter() != null ? this.getSequenceFilter().getName() : null ) );

        }

        return this.sequences;
    }

    /**
     * Notifies any available {@code ExceptionListener} whenever a recoverable exception has been caught.
     *
     * @param e The exception that was caught.
     */
    protected void fireExceptionThrown( final Exception e )
    {
        for ( ExceptionListener l : this.getExceptionListener() )
        {
            l.exceptionThrown( e );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public SequencesTableModel()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code ExceptionListener} dependency.
     * <p>This method returns any available object of the {@code java.beans.ExceptionListener} specification at specification level 1.4.</p>
     * @return The {@code ExceptionListener} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.beans.ExceptionListener[] getExceptionListener() throws org.jomc.ObjectManagementException
    {
        return (java.beans.ExceptionListener[]) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "ExceptionListener" );
    }

    /**
     * Gets the {@code Locale} dependency.
     * <p>This method returns the "{@code default}" object of the {@code java.util.Locale} specification at specification level 1.1.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.util.Locale getLocale() throws org.jomc.ObjectManagementException
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Locale" );
    }

    /**
     * Gets the {@code Logger} dependency.
     * <p>This method returns any available object of the {@code org.jomc.logging.Logger} specification at specification level 1.0.</p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.sequences.util.SequencesTableModel".
     * </dd>
     * </dl>
     * @return The {@code Logger} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.logging.Logger getLogger() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.logging.Logger) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Logger" );
    }

    /**
     * Gets the {@code SequenceDirectory} dependency.
     * <p>This method returns any available object of the {@code org.jomc.sequences.SequenceDirectory} specification at specification level 1.0.</p>
     * @return The {@code SequenceDirectory} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.sequences.SequenceDirectory getSequenceDirectory() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.SequenceDirectory) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SequenceDirectory" );
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code incrementColumnEditableByDefault} property.
     * @return Flag indicating that the "increment" column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.Boolean isIncrementColumnEditableByDefault() throws org.jomc.ObjectManagementException
    {
        return (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "incrementColumnEditableByDefault" );
    }

    /**
     * Gets the value of the {@code maximumColumnEditableByDefault} property.
     * @return Flag indicating that the "maximum" column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.Boolean isMaximumColumnEditableByDefault() throws org.jomc.ObjectManagementException
    {
        return (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "maximumColumnEditableByDefault" );
    }

    /**
     * Gets the value of the {@code minimumColumnEditableByDefault} property.
     * @return Flag indicating that the "minimum" column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.Boolean isMinimumColumnEditableByDefault() throws org.jomc.ObjectManagementException
    {
        return (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "minimumColumnEditableByDefault" );
    }

    /**
     * Gets the value of the {@code nameColumnEditableByDefault} property.
     * @return Flag indicating that the "name" column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.Boolean isNameColumnEditableByDefault() throws org.jomc.ObjectManagementException
    {
        return (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "nameColumnEditableByDefault" );
    }

    /**
     * Gets the value of the {@code valueColumnEditableByDefault} property.
     * @return Flag indicating that the "value" column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.Boolean isValueColumnEditableByDefault() throws org.jomc.ObjectManagementException
    {
        return (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "valueColumnEditableByDefault" );
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code illegalColumnIndex} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal column index {0}. {1}</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ungültiger Spalten-Index {0}. {1}</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param columnIndex Format argument.
     * @return The text of the {@code illegalColumnIndex} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getIllegalColumnIndexMessage( final java.util.Locale locale, final java.lang.Number columnIndex ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "illegalColumnIndex", locale, new Object[] { columnIndex, null } );
    }

    /**
     * Gets the text of the {@code incrementColumnTitle} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Increment</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Inkrement</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code incrementColumnTitle} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getIncrementColumnTitleMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "incrementColumnTitle", locale,  null );
    }

    /**
     * Gets the text of the {@code maximumColumnTitle} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Maximum</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Maximum</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code maximumColumnTitle} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getMaximumColumnTitleMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "maximumColumnTitle", locale,  null );
    }

    /**
     * Gets the text of the {@code minimumColumnTitle} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Minimum</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Minimum</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code minimumColumnTitle} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getMinimumColumnTitleMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "minimumColumnTitle", locale,  null );
    }

    /**
     * Gets the text of the {@code nameColumnTitle} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Name</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Name</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code nameColumnTitle} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getNameColumnTitleMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "nameColumnTitle", locale,  null );
    }

    /**
     * Gets the text of the {@code valueColumnTitle} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Value</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Wert</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code valueColumnTitle} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getValueColumnTitleMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "valueColumnTitle", locale,  null );
    }
    // SECTION-END
}
