// SECTION-START[License Header]
/*
 *  JOMC Sequences RI
 *  Copyright (C) 2005 Christian Schulte <cs@schulte.it>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *  $Id$
 */
// SECTION-END
package org.jomc.sequences.ri;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import org.jomc.sequences.CapacityLimitException;
import org.jomc.sequences.ConcurrentModificationException;
import org.jomc.sequences.DuplicateSequenceException;
import org.jomc.sequences.IllegalSequenceException;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceDirectory;
import org.jomc.sequences.SequenceLimitException;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequenceObserver;
import org.jomc.sequences.SequenceOperations;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.model.SequenceType;
import org.jomc.sequences.model.SequencesType;
import org.jomc.sequences.spi.SequenceValidator;

// SECTION-START[Implementation Comment]
/**
 * SequenceDirectory reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.SequenceOperations} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.
 * State does not need to be retained across operations to operate as
 * specified.</blockquote></li>
 * <li>{@code org.jomc.sequences.SequenceDirectory} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.
 * State does not need to be retained across operations to operate as
 * specified.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getDirectoryName directoryName}"<blockquote>
 * Property of type {@code java.lang.String} with value "JOMC Sequences RI".</blockquote></li>
 * <li>"{@link #isContainerManaged containerManaged}"<blockquote>
 * Property of type {@code boolean} with value "false".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getSequenceObserver SequenceObserver}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceObserver} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getSequenceValidator SequenceValidator}"<blockquote>
 * Dependency on {@code org.jomc.sequences.spi.SequenceValidator} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getSequenceMapper SequenceMapper}"<blockquote>
 * Dependency on {@code org.jomc.sequences.ri.SequenceMapper} at specification level 1.0 applying to Singleton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getEntityManager EntityManager}"<blockquote>
 * Dependency on {@code javax.persistence.EntityManager} applying to Multiton scope.</blockquote></li>
 * <li>"{@link #getUserTransaction UserTransaction}"<blockquote>
 * Dependency on {@code javax.transaction.UserTransaction} applying to Multiton scope.</blockquote></li>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getImplementationInfoMessage implementationInfo}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>DefaultSequenceDirectory 1.0-alpha-1-SNAPSHOT
     * {0}</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>DefaultSequenceDirectory 1.0-alpha-1-SNAPSHOT
     * {0}</pre></td></tr>
 * </table>
 * <li>"{@link #getCouldNotJoinTransactionMessage couldNotJoinTransaction}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Could not join transaction with status ''{0,number}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Konnte Transaktion mit Status ''{0,number}'' nicht beitreten.</pre></td></tr>
 * </table>
 * <li>"{@link #getSuccessfullyStartedTransactionMessage successfullyStartedTransaction}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Started new transaction with status ''{0}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Neue Transaktion mit Status ''{0}'' gestartet.</pre></td></tr>
 * </table>
 * <li>"{@link #getSuccessfullyJoinedTransactionMessage successfullyJoinedTransaction}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Joined active transaction with status ''{0,number}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktueller Transaktion mit Status ''{0}'' beigetreten.</pre></td></tr>
 * </table>
 * <li>"{@link #getSuccessfullyCommittedTransactionMessage successfullyCommittedTransaction}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Committed active transaction with status ''{0}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktuelle Transaktion mit Status ''{0}'' festgeschrieben.</pre></td></tr>
 * </table>
 * <li>"{@link #getSuccessfullyRolledBackTransactionMessage successfullyRolledBackTransaction}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Rolled back active transaction with status ''{0}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktuelle Transaktion mit Status ''{0}'' zurückgenommen.</pre></td></tr>
 * </table>
 * <li>"{@link #getCreatedSequenceDirectoryMessage createdSequenceDirectory}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Sequence directory ''{0}'' created.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Sequenzverzeichnis ''{0}'' erstellt.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class DefaultSequenceDirectory
    implements SequenceDirectory, SequenceOperations
{
    // SECTION-START[SequenceDirectory]

    public BigInteger getSequenceCount()
    {
        boolean commit = false;
        boolean rollback = false;
        BigInteger count = null;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            final Query query = this.getEntityManager().createNamedQuery( COUNT_SEQUENCES_QUERY );
            query.setParameter( 1, this.getSequencesList().getName() );

            count = BigInteger.valueOf( ( (Long) query.getSingleResult() ).longValue() );

            if ( commit )
            {
                this.commitTransaction();
            }

            return count;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    public BigInteger getCapacityLimit()
    {
        return CAPACITY_LIMIT;
    }

    public Sequence getSequence( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            final SequenceType sequenceType = this.getSequenceByName( name );
            final Sequence s =
                sequenceType == null ? null : this.getSequenceMapper().map( sequenceType, new Sequence() );

            if ( commit )
            {
                this.commitTransaction();
            }

            return s;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    public Sequence addSequence( final Sequence sequence ) throws CapacityLimitException
    {
        if ( sequence == null )
        {
            throw new NullPointerException( "sequence" );
        }

        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            this.assertMaximumCapacityNotReached();
            this.assertOperationValid( null, sequence );

            SequenceType sequenceType = this.getSequenceByName( sequence.getName() );

            if ( sequenceType != null )
            {
                throw new DuplicateSequenceException( this.getSequenceMapper().map( sequenceType, new Sequence() ) );
            }

            sequenceType = this.getSequenceMapper().map( sequence, new SequenceType() );
            sequenceType.setJpaDate( Calendar.getInstance() );

            final SequencesType sequences = this.getSequencesList();
            this.getEntityManager().persist( sequenceType );
            sequences.getSequence().add( sequenceType );
            this.getEntityManager().merge( sequences );

            final Sequence persistent = this.getSequenceMapper().map( sequenceType, new Sequence() );

            if ( commit )
            {
                this.commitTransaction();
            }

            this.notifyObservers( null, persistent );

            return persistent;
        }
        catch ( CapacityLimitException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( IllegalSequenceException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( DuplicateSequenceException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    public Sequence editSequence( final String name, final long revision, final Sequence sequence )
        throws ConcurrentModificationException
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }
        if ( sequence == null )
        {
            throw new NullPointerException( "sequence" );
        }

        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            SequenceType sequenceType = this.getSequenceByName( name );

            if ( sequenceType == null )
            {
                throw new SequenceNotFoundException( name );
            }
            if ( sequenceType.getRevision() != revision )
            {
                throw new ConcurrentModificationException(
                    this.getSequenceMapper().map( sequenceType, new Sequence() ) );

            }

            final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );
            this.assertOperationValid( oldValue, sequence );

            sequenceType = this.getSequenceMapper().map( sequence, sequenceType );
            sequenceType.setRevision( sequenceType.getRevision() + 1L );
            sequenceType.setJpaDate( Calendar.getInstance() );
            this.getEntityManager().merge( sequenceType );

            final Sequence newValue = this.getSequenceMapper().map( sequenceType, new Sequence() );

            if ( commit )
            {
                this.commitTransaction();
            }

            this.notifyObservers( oldValue, newValue );

            return newValue;
        }
        catch ( SequenceNotFoundException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( ConcurrentModificationException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( IllegalSequenceException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    public Sequence deleteSequence( final String name, final long revision )
        throws ConcurrentModificationException
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            final SequenceType sequenceType = this.getSequenceByName( name );

            if ( sequenceType == null )
            {
                throw new SequenceNotFoundException( name );
            }
            if ( sequenceType.getRevision() != revision )
            {
                throw new ConcurrentModificationException(
                    this.getSequenceMapper().map( sequenceType, new Sequence() ) );

            }

            final Sequence deleted = this.getSequenceMapper().map( sequenceType, new Sequence() );
            this.assertOperationValid( deleted, null );

            final SequencesType sequences = this.getSequencesList();
            sequences.getSequence().remove( sequenceType );
            this.getEntityManager().remove( sequenceType );

            if ( sequences.getSequence().isEmpty() )
            {
                this.getEntityManager().remove( sequences );
            }
            else
            {
                this.getEntityManager().merge( sequences );
            }

            final Sequence s = this.getSequenceMapper().map( sequenceType, new Sequence() );

            if ( commit )
            {
                this.commitTransaction();
            }

            this.notifyObservers( s, null );
            return deleted;
        }
        catch ( IllegalSequenceException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequenceNotFoundException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( ConcurrentModificationException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    public Set<Sequence> searchSequences( final String name )
    {
        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            final Query query = this.getEntityManager().createNamedQuery(
                name == null ? FIND_ALL_SEQUENCES_QUERY : SEARCH_SEQUENCES_QUERY );

            query.setParameter( 1, this.getSequencesList().getName() );

            if ( name != null )
            {
                query.setParameter( 2, "%" + name + "%" );
            }

            final List<SequenceType> resultList = (List<SequenceType>) query.getResultList();
            final Set<Sequence> sequences = new HashSet<Sequence>( resultList.size() );

            for ( SequenceType s : resultList )
            {
                sequences.add( this.getSequenceMapper().map( s, new Sequence() ) );
            }

            if ( commit )
            {
                this.commitTransaction();
            }

            return sequences;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    // SECTION-END
    // SECTION-START[SequenceOperations]

    public long getNextSequenceValue( final String sequenceName ) throws SequenceLimitException
    {
        if ( sequenceName == null )
        {
            throw new NullPointerException( "sequenceName" );
        }

        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            SequenceType sequenceType = this.getSequenceByName( sequenceName );

            if ( sequenceType == null )
            {
                throw new SequenceNotFoundException( sequenceName );
            }

            final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );
            final long nextValue = sequenceType.getValue() + sequenceType.getIncrement();

            if ( nextValue < sequenceType.getValue() || nextValue > sequenceType.getMaximum() )
            {
                throw new SequenceLimitException( sequenceType.getValue() );
            }

            sequenceType.setValue( nextValue );
            sequenceType.setRevision( sequenceType.getRevision() + 1L );
            sequenceType.setJpaDate( Calendar.getInstance() );

            this.getEntityManager().merge( sequenceType );

            final Sequence s = this.getSequenceMapper().map( sequenceType, new Sequence() );

            if ( commit )
            {
                this.commitTransaction();
            }

            this.notifyObservers( oldValue, s );
            return nextValue;
        }
        catch ( SequenceNotFoundException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequenceLimitException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    public long[] getNextSequenceValues( final String sequenceName, final int numValues )
        throws SequenceLimitException
    {
        if ( sequenceName == null )
        {
            throw new NullPointerException( "sequenceName" );
        }
        if ( numValues < 0 )
        {
            throw new IllegalArgumentException( Integer.toString( numValues ) );
        }

        boolean commit = false;
        boolean rollback = false;

        try
        {
            if ( !this.isContainerManaged() )
            {
                commit = this.beginTransaction();
            }

            SequenceType sequenceType = this.getSequenceByName( sequenceName );

            if ( sequenceType == null )
            {
                throw new SequenceNotFoundException( sequenceName );
            }

            final long[] values = new long[ numValues ];
            final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );

            for ( int i = values.length - 1; i >= 0; i-- )
            {
                final long nextValue = sequenceType.getValue() + sequenceType.getIncrement();

                if ( nextValue < sequenceType.getValue() || nextValue > sequenceType.getMaximum() )
                {
                    throw new SequenceLimitException( sequenceType.getValue() );
                }

                sequenceType.setValue( nextValue );
                values[i] = nextValue;
            }

            sequenceType.setRevision( sequenceType.getRevision() + 1L );
            sequenceType.setJpaDate( Calendar.getInstance() );

            this.getEntityManager().merge( sequenceType );

            final Sequence s = this.getSequenceMapper().map( sequenceType, new Sequence() );

            if ( commit )
            {
                this.commitTransaction();
            }

            this.notifyObservers( oldValue, s );

            return values;
        }
        catch ( SequenceNotFoundException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequenceLimitException e )
        {
            this.getLogger().error( e.getMessage() );
            rollback = true;
            throw e;
        }
        catch ( SequencesSystemException e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw e;
        }
        catch ( Exception e )
        {
            this.getLogger().fatal( e );
            rollback = true;
            throw new SequencesSystemException( e );
        }
        finally
        {
            if ( !this.isContainerManaged() && rollback )
            {
                this.rollbackTransaction();
            }
        }
    }

    // SECTION-END
    // SECTION-START[JaxbSequenceDirectory]

    /** Constant for the name of the query for counting sequences. */
    private static final String COUNT_SEQUENCES_QUERY = "jomc-sequences-model-count-sequences";

    /** Constant for the name of the query for finding all sequences. */
    private static final String FIND_ALL_SEQUENCES_QUERY = "jomc-sequences-model-find-all-sequences";

    /** Constant for the name of the query for searching sequences. */
    private static final String SEARCH_SEQUENCES_QUERY = "jomc-sequences-model-search-sequences";

    /** Constant for the name of the query for finding a sequence by name. */
    private static final String FIND_SEQUENCE_BY_NAME_QUERY = "jomc-sequences-model-find-sequence-by-name";

    /** Constant for the name of the query for finding a sequence list by name. */
    private static final String FIND_SEQUENCES_BY_NAME_QUERY = "jomc-sequences-model-find-sequences-by-name";

    /** Capacity limit. */
    private static final BigInteger CAPACITY_LIMIT = BigInteger.valueOf( Integer.MAX_VALUE );

    /**
     * Gets a sequence for a given name.
     *
     * @param name The name of the sequence to return.
     *
     * @return The sequence with name {@code name}, or {@code null} if no sequence matching {@code name} exists.
     */
    protected SequenceType getSequenceByName( final String name )
    {
        try
        {
            final Query query = this.getEntityManager().createNamedQuery( FIND_SEQUENCE_BY_NAME_QUERY );
            query.setParameter( 1, this.getSequencesList().getName() );
            query.setParameter( 2, name );

            return (SequenceType) query.getSingleResult();
        }
        catch ( NoResultException e )
        {
            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( e.getMessage() );
            }

            return null;
        }
    }

    /**
     * Gets the list of sequences matching this implementations directory name.
     *
     * @return The list of sequences matching this implementations directory name.
     */
    protected SequencesType getSequencesList()
    {
        final Query query = this.getEntityManager().createNamedQuery( FIND_SEQUENCES_BY_NAME_QUERY );
        query.setParameter( 1, this.getDirectoryName() );

        SequencesType sequencesType = null;

        try
        {
            sequencesType = (SequencesType) query.getSingleResult();
        }
        catch ( NoResultException e )
        {
            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( e.getMessage() );
            }

            sequencesType = new SequencesType();
            sequencesType.setName( this.getDirectoryName() );
            sequencesType.setJpaDate( Calendar.getInstance() );
            this.getEntityManager().persist( sequencesType );

            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( this.getCreatedSequenceDirectoryMessage(
                    this.getLocale(), this.getDirectoryName() ) );

            }
        }

        return sequencesType;
    }

    /**
     * Begins a transaction.
     *
     * @return {@code true} if a new transaction was associated with the current context. {@code false} if the current
     * context already had a transaction associated.
     *
     * @throws SequencesSystemException if beginning a transaction fails.
     */
    protected boolean beginTransaction()
    {
        try
        {
            final UserTransaction tx = this.getUserTransaction();
            final int status = tx.getStatus();

            if ( status == Status.STATUS_NO_TRANSACTION )
            {
                tx.begin();

                if ( this.getLogger().isDebugEnabled() )
                {
                    this.getLogger().debug( this.getSuccessfullyStartedTransactionMessage(
                        this.getLocale(), tx.getStatus() ) );

                }

                return true;
            }

            if ( tx.getStatus() != Status.STATUS_ACTIVE )
            {
                throw new SequencesSystemException( this.getCouldNotJoinTransactionMessage(
                    this.getLocale(), tx.getStatus() ) );

            }

            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( this.getSuccessfullyJoinedTransactionMessage(
                    this.getLocale(), tx.getStatus() ) );

            }

            return false;
        }
        catch ( SequencesSystemException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new SequencesSystemException( e );
        }
    }

    /**
     * Commits the current transaction.
     *
     * @throws SequencesSystemException if committing the current transaction fails.
     */
    protected void commitTransaction()
    {
        try
        {
            this.getUserTransaction().commit();

            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( this.getSuccessfullyCommittedTransactionMessage(
                    this.getLocale(), this.getUserTransaction().getStatus() ) );

            }
        }
        catch ( Exception e )
        {
            throw new SequencesSystemException( e );
        }
    }

    /**
     * Marks the current transaction for rollback.
     *
     * @throws SequencesSystemException if marking the current transaction for rollback fails.
     */
    protected void rollbackTransaction()
    {
        try
        {
            this.getUserTransaction().rollback();

            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( this.getSuccessfullyRolledBackTransactionMessage(
                    this.getLocale(), this.getUserTransaction().getStatus() ) );

            }
        }
        catch ( Exception e )
        {
            throw new SequencesSystemException( e );
        }
    }

    /**
     * Checks the state change of a sequence in the directory by calling any available {@code SequenceValidator}
     * implementation in the system.
     *
     * @param oldValue The entity getting changed or {@code null} if {@code newValue} is about to be added to the
     * directory.
     * @param newValue The value {@code oldValue} will be changed to or {@code null} if {@code oldValue} is about to be
     * removed from the directory.
     *
     * @throws IllegalSequenceException if the set of available {@code SequenceValidator} implementations prevents the
     * operation from taking place.
     */
    protected void assertOperationValid( final Sequence oldValue, final Sequence newValue )
    {
        final SequenceValidator[] validators = this.getSequenceValidator();

        IllegalSequenceException result = null;
        for ( int i = validators.length - 1; i >= 0; i-- )
        {
            final IllegalSequenceException current = validators[i].assertOperationValid( oldValue, newValue );

            if ( current != null )
            {
                if ( result != null )
                {
                    for ( String propertyName : current.getPropertyNames() )
                    {
                        result.getDetails( propertyName ).addAll( current.getDetails( propertyName ) );
                    }

                    result.getDetails().addAll( current.getDetails() );
                }
                else
                {
                    result = current;
                }
            }
        }

        if ( result != null )
        {
            this.injectFieldValue( result, "locked", Boolean.TRUE );
            throw result;
        }
    }

    /**
     * Notifies any observers by calling any available {@code SequenceObserver} implementation in the system.
     *
     * @param oldValue The entity having been changed or {@code null} if {@code newValue} got added to the directory.
     * @param newValue The value {@code oldValue} got changed to or {@code null} if {@code oldValue} got removed from
     * the directory.
     */
    protected void notifyObservers( final Sequence oldValue, final Sequence newValue )
    {
        final SequenceObserver[] observers = this.getSequenceObserver();

        if ( observers != null )
        {
            for ( int i = observers.length - 1; i >= 0; i-- )
            {
                observers[i].onSequenceChange( oldValue, newValue );
            }
        }
    }

    /**
     * Checks the model to not have reached its maximum capacity.
     *
     * @throws CapacityLimitException if the model reached its maximum capacity.
     */
    protected void assertMaximumCapacityNotReached() throws CapacityLimitException
    {
        final Query query = this.getEntityManager().createNamedQuery( COUNT_SEQUENCES_QUERY );
        query.setParameter( 1, this.getSequencesList().getName() );

        final BigInteger count = BigInteger.valueOf( ( (Long) query.getSingleResult() ).longValue() );
        if ( count.longValue() >= CAPACITY_LIMIT.longValue() )
        {
            throw new CapacityLimitException( CAPACITY_LIMIT );
        }
    }

    /**
     * Sets the value of a field of a given object using reflection.
     *
     * @param object The object to update.
     * @param fieldName The name of the field to update.
     * @param value The new value for field {@code fieldName}.
     *
     * @throws SequencesSystemException if setting {@code value} fails unexpectedly.
     */
    private void injectFieldValue( final Object object, final String fieldName, final Object value )
        throws SequencesSystemException
    {
        AccessController.doPrivileged( new PrivilegedAction<Object>()
        {

            public Object run()
            {
                Field field = null;

                try
                {
                    field = object.getClass().getDeclaredField( fieldName );
                    field.setAccessible( true );
                    field.set( object, value );
                    field.setAccessible( false );
                    return null;
                }
                catch ( Exception e )
                {
                    throw new SequencesSystemException( e );
                }
            }

        } );
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public DefaultSequenceDirectory()
    {
        // SECTION-START[Default Constructor]
        super();

        if ( this.getLogger().isDebugEnabled() )
        {
            final StringBuffer printout = new StringBuffer( 1024 ).append( '{' );
            printout.append( "containerManaged=" ).append( this.isContainerManaged() ).append( ", " );
            printout.append( "directoryName=" ).append( this.getDirectoryName() ).append( '}' );
            this.getLogger().debug( this.getImplementationInfoMessage( this.getLocale(), printout.toString() ) );
        }
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code EntityManager} dependency.
     * </p>
     * @return The {@code EntityManager} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private javax.persistence.EntityManager getEntityManager() throws org.jomc.ObjectManagementException
    {
        return (javax.persistence.EntityManager) org.jomc.ObjectManager.getInstance().getDependency( this, "EntityManager" );
    }

    /**
     * Gets the {@code Locale} dependency.
     * </p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.util.Locale getLocale() throws org.jomc.ObjectManagementException
    {
        return (java.util.Locale) org.jomc.ObjectManager.getInstance().getDependency( this, "Locale" );
    }

    /**
     * Gets the {@code Logger} dependency.
     * </p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.sequences.ri.DefaultSequenceDirectory".
     * </dd>
     * </dl>
     * @return The {@code Logger} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.logging.Logger getLogger() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.logging.Logger) org.jomc.ObjectManager.getInstance().getDependency( this, "Logger" );
    }

    /**
     * Gets the {@code SequenceMapper} dependency.
     * </p>
     * @return The {@code SequenceMapper} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.sequences.ri.SequenceMapper getSequenceMapper() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.ri.SequenceMapper) org.jomc.ObjectManager.getInstance().getDependency( this, "SequenceMapper" );
    }

    /**
     * Gets the {@code SequenceObserver} dependency.
     * </p>
     * @return The {@code SequenceObserver} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.sequences.SequenceObserver[] getSequenceObserver() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.SequenceObserver[]) org.jomc.ObjectManager.getInstance().getDependency( this, "SequenceObserver" );
    }

    /**
     * Gets the {@code SequenceValidator} dependency.
     * </p>
     * @return The {@code SequenceValidator} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.sequences.spi.SequenceValidator[] getSequenceValidator() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.spi.SequenceValidator[]) org.jomc.ObjectManager.getInstance().getDependency( this, "SequenceValidator" );
    }

    /**
     * Gets the {@code UserTransaction} dependency.
     * </p>
     * @return The {@code UserTransaction} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private javax.transaction.UserTransaction getUserTransaction() throws org.jomc.ObjectManagementException
    {
        return (javax.transaction.UserTransaction) org.jomc.ObjectManager.getInstance().getDependency( this, "UserTransaction" );
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code containerManaged} property.
     * @return Flag indicating container managed transaction demarcation. Set to true when using in an EJB environment.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private boolean isContainerManaged() throws org.jomc.ObjectManagementException
    {
        return ( (java.lang.Boolean) org.jomc.ObjectManager.getInstance().getProperty( this, "containerManaged" ) ).booleanValue();
    }

    /**
     * Gets the value of the {@code directoryName} property.
     * @return Name uniquely identifying the directory in a set of directories.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDirectoryName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "directoryName" );
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code couldNotJoinTransaction} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Could not join transaction with status ''{0,number}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Konnte Transaktion mit Status ''{0,number}'' nicht beitreten.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param status Format argument.
     * @return The text of the {@code couldNotJoinTransaction} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getCouldNotJoinTransactionMessage( final java.util.Locale locale, final java.lang.Number status ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "couldNotJoinTransaction", locale, new Object[] { status, null } );
    }

    /**
     * Gets the text of the {@code createdSequenceDirectory} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Sequence directory ''{0}'' created.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Sequenzverzeichnis ''{0}'' erstellt.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param name Format argument.
     * @return The text of the {@code createdSequenceDirectory} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getCreatedSequenceDirectoryMessage( final java.util.Locale locale, final java.lang.String name ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "createdSequenceDirectory", locale, new Object[] { name, null } );
    }

    /**
     * Gets the text of the {@code implementationInfo} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>DefaultSequenceDirectory 1.0-alpha-1-SNAPSHOT
     * {0}</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>DefaultSequenceDirectory 1.0-alpha-1-SNAPSHOT
     * {0}</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param configurationPrintout Format argument.
     * @return The text of the {@code implementationInfo} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getImplementationInfoMessage( final java.util.Locale locale, final java.lang.String configurationPrintout ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "implementationInfo", locale, new Object[] { configurationPrintout, null } );
    }

    /**
     * Gets the text of the {@code successfullyCommittedTransaction} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Committed active transaction with status ''{0}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktuelle Transaktion mit Status ''{0}'' festgeschrieben.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param status Format argument.
     * @return The text of the {@code successfullyCommittedTransaction} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getSuccessfullyCommittedTransactionMessage( final java.util.Locale locale, final java.lang.Number status ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "successfullyCommittedTransaction", locale, new Object[] { status, null } );
    }

    /**
     * Gets the text of the {@code successfullyJoinedTransaction} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Joined active transaction with status ''{0,number}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktueller Transaktion mit Status ''{0}'' beigetreten.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param status Format argument.
     * @return The text of the {@code successfullyJoinedTransaction} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getSuccessfullyJoinedTransactionMessage( final java.util.Locale locale, final java.lang.Number status ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "successfullyJoinedTransaction", locale, new Object[] { status, null } );
    }

    /**
     * Gets the text of the {@code successfullyRolledBackTransaction} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Rolled back active transaction with status ''{0}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktuelle Transaktion mit Status ''{0}'' zurückgenommen.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param status Format argument.
     * @return The text of the {@code successfullyRolledBackTransaction} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getSuccessfullyRolledBackTransactionMessage( final java.util.Locale locale, final java.lang.Number status ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "successfullyRolledBackTransaction", locale, new Object[] { status, null } );
    }

    /**
     * Gets the text of the {@code successfullyStartedTransaction} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Started new transaction with status ''{0}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Neue Transaktion mit Status ''{0}'' gestartet.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param status Format argument.
     * @return The text of the {@code successfullyStartedTransaction} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getSuccessfullyStartedTransactionMessage( final java.util.Locale locale, final java.lang.Number status ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "successfullyStartedTransaction", locale, new Object[] { status, null } );
    }
    // SECTION-END
}
