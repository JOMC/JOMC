/*
 *  JOMC Standalone
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
package org.jomc.standalone.naming.support;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import org.enhydra.jdbc.standard.StandardXADataSource;
import org.jomc.standalone.Environment;

/**
 * Provides static methods supporting XAPool.
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
class XAPoolHelper
{

    static void initializeXAPool( final Environment environment, final Context context ) throws NamingException
    {
        final TransactionManager transactionManager =
            (TransactionManager) context.lookup( environment.getTransactionManagerJndiName() );

        if ( context.lookup( environment.getDataSourceJndiName() ) instanceof StandardXADataSource )
        {
            final StandardXADataSource ds =
                (StandardXADataSource) context.lookup( environment.getDataSourceJndiName() );

            ds.setTransactionManager( transactionManager );
        }
        if ( context.lookup( environment.getJtaDataSourceJndiName() ) instanceof StandardXADataSource )
        {
            final StandardXADataSource ds =
                (StandardXADataSource) context.lookup( environment.getJtaDataSourceJndiName() );

            ds.setTransactionManager( transactionManager );
        }
    }

}
