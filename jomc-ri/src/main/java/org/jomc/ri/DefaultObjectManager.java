// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2009 - 2011 The JOMC Project
 *   Copyright (c) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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
// </editor-fold>
// SECTION-END
package org.jomc.ri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import org.jomc.ObjectManagementException;
import org.jomc.ObjectManager;
import org.jomc.ObjectManagerFactory;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.ImplementationReference;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Message;
import org.jomc.model.ModelObject;
import org.jomc.model.Module;
import org.jomc.model.Modules;
import org.jomc.model.Multiplicity;
import org.jomc.model.Property;
import org.jomc.model.PropertyException;
import org.jomc.model.Specification;
import org.jomc.model.SpecificationReference;
import org.jomc.model.Specifications;
import org.jomc.model.modlet.ModelHelper;
import org.jomc.modlet.Model;
import org.jomc.modlet.ModelContext;
import org.jomc.modlet.ModelException;
import org.jomc.modlet.ModelValidationReport;
import org.jomc.spi.Invocation;
import org.jomc.spi.Invoker;
import org.jomc.spi.Listener;
import org.jomc.spi.Locator;
import org.jomc.spi.Scope;
import org.jomc.util.WeakIdentityHashMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Default {@code ObjectManager} implementation.
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="4" nowrap><font size="+2">Specifications</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Identifier</b></td>
 *       <td align="left" scope="col" nowrap><b>Class</b></td>
 *       <td align="left" scope="col" nowrap><b>Scope</b></td>
 *       <td align="left" scope="col" nowrap><b>Version</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ObjectManager}</td>
 *       <td align="left" nowrap>{@code org.jomc.ObjectManager}</td>
 *       <td align="left" nowrap>{@code Singleton}</td>
 *       <td align="left" nowrap>{@code 1.0}</td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="3" nowrap><font size="+2">Messages</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Languages</b></td>
 *       <td align="left" scope="col" nowrap><b>Default Templates</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getCreatingModulesInfo creatingModulesInfo}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Creating modules for ''{0}''.</code></pre><hr/><pre><code>Erstellt Module f&uuml;r ''{0}''.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultImplementationName defaultImplementationName}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Default JOMC RI</code></pre><hr/><pre><code>Default JOMC RI</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultInvokerInfoMessage defaultInvokerInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultInvoker Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 for ''{0}''.</code></pre><hr/><pre><code>DefaultInvoker Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 f&uuml;r ''{0}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultListenerInfo defaultListenerInfo}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultListener Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 for ''{0}''.</code></pre><hr/><pre><code>DefaultListener Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 f&uuml;r ''{0}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultLocatorInfoMessage defaultLocatorInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultLocator Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scheme ''{0}'' for ''{1}''.</code></pre><hr/><pre><code>DefaultLocator Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scheme ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultLogLevelInfoMessage defaultLogLevelInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Default log level: ''{0}''</code></pre><hr/><pre><code>Standard-Protokollierungsstufe: ''{0}''</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModelIdentifierInfo defaultModelIdentifierInfo}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Default model identifier: ''{0}''</code></pre><hr/><pre><code>Standard Modellbezeichner: ''{0}''</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModelObjectClasspahResolutionEnabledInfo defaultModelObjectClasspahResolutionEnabledInfo}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Default model object classpath resolution enabled: ''{0}''</code></pre><hr/><pre><code>Standard Modellobjekt-Klassenpfad-Aufl&ouml;sung aktiviert: ''{0}''</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModelProcessingEnabledInfo defaultModelProcessingEnabledInfo}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Default model processing enabled: ''{0}''</code></pre><hr/><pre><code>Standard Modellverarbeitung aktiviert: ''{0}''</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModuleName defaultModuleName}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Default JOMC RI</code></pre><hr/><pre><code>Default JOMC RI</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModulesVendor defaultModulesVendor}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>The JOMC Project</code></pre><hr/><pre><code>The JOMC Project</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModulesVersion defaultModulesVersion}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>1.2-SNAPSHOT</code></pre><hr/><pre><code>1.2-SNAPSHOT</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultModulesWarning defaultModulesWarning}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Failure loading model ''{0}'' for ''{1}''. Creating default modules.</code></pre><hr/><pre><code>Fehler beim Laden des Modells ''{0}'' f&uuml;r ''{1}''. Erstellt Standard-Module.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDefaultScopeInfoMessage defaultScopeInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultScope Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scope ''{0}'' for ''{1}''.</code></pre><hr/><pre><code>DefaultScope Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scope ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getDependencyCycleMessage dependencyCycleMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>A dependency of implementation ''{0}'' introduces a cycle.</code></pre><hr/><pre><code>Zyklische Anforderung der Implementierung ''{0}''.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getIgnoredInvocationMessage ignoredInvocationMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Invocation implementation ''{0}'' ignored.</code></pre><hr/><pre><code>Invocation-Implementierung ''{0}'' ignoriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getIgnoredInvokerMessage ignoredInvokerMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Invoker implementation ''{0}'' ignored.</code></pre><hr/><pre><code>Invoker-Implementierung ''{0}'' ignoriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getIllegalArraySpecificationMessage illegalArraySpecificationMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Illegal array specification ''{0}''. Mutliplicity ''{1}''.</code></pre><hr/><pre><code>Ung&uuml;ltige ''Array''-Spezifikation ''{0}''. Kardinalit&auml;t ''{1}''.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getIllegalObjectSpecificationMessage illegalObjectSpecificationMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Illegal object specification ''{0}''. Multiplicity ''{1}''.</code></pre><hr/><pre><code>Ung&uuml;ltige ''Object''-Spezifikation ''{0}''. Kardinalit&auml;t ''{1}''.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getImplementationInfoMessage implementationInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>DefaultObjectManager Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 initialized in {0,number}ms.</code></pre><hr/><pre><code>DefaultObjectManager Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 in {0,number}ms initialisiert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getInvokerInfoMessage invokerInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered invoker implementation ''{0}'' for ''{1}''.</code></pre><hr/><pre><code>Invoker-Implementierung ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getListenerInfoMessage listenerInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered listener implementation ''{0}'' for ''{1}''.</code></pre><hr/><pre><code>Listener-Implementierung ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getLocatorInfoMessage locatorInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered ''{1}'' location URI scheme locator implementation ''{0}'' for ''{2}''.</code></pre><hr/><pre><code>''{1}''-Locator-URI-Schema-Implementierung ''{0}'' f&uuml;r ''{2}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingDependencyMessage missingDependencyMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Dependency ''{1}'' not found for implementation ''{0}''.</code></pre><hr/><pre><code>''{1}''-Anforderung der Implementierung ''{0}'' nicht gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingImplementationMessage missingImplementationMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Implementation ''{1}'' not found for specification ''{0}''.</code></pre><hr/><pre><code>Implementierung ''{1}'' der Spezifikation ''{0}'' nicht gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingImplementationsMessage missingImplementationsMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>No implementations found for specification ''{0}''.</code></pre><hr/><pre><code>Keine Implementierungen der Spezifikation ''{0}'' gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingInstanceMessage missingInstanceMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>No instance found for implementation ''{0}'' - ''{1}''.</code></pre><hr/><pre><code>Keine Instanz f&uuml;r Implementierung ''{0}'' - ''{1}'' gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingLocatorMessage missingLocatorMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>No locator found for location ''{0}''.</code></pre><hr/><pre><code>Keinen Locator f&uuml;r Ort ''{0}'' gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingMessageMessage missingMessageMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Message ''{1}'' not found for implementation ''{0}''.</code></pre><hr/><pre><code>''{1}''-Meldung der Implementierung ''{0}'' nicht gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingObjectInstanceMessage missingObjectInstanceMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>No instance found for object ''{0}''.</code></pre><hr/><pre><code>Keine Instanz f&uuml;r Objekt ''{0}'' gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingObjectMessage missingObjectMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>No object found for implementation ''{0}'' - ''{1}''.</code></pre><hr/><pre><code>Kein Objekt f&uuml;r Implementierung ''{0}'' - ''{1}'' gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingPropertyMessage missingPropertyMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Property ''{1}'' not found for implementation ''{0}''.</code></pre><hr/><pre><code>''{1}''-Eigenschaft der Implementierung ''{0}'' nicht gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingScopeMessage missingScopeMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Scope ''{0}'' not found.</code></pre><hr/><pre><code>G&uuml;ltigkeitsbereich ''{0}'' nicht gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingSpecificationClassMessage missingSpecificationClassMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Specification ''{0}'' does not define a class.</code></pre><hr/><pre><code>Spezifikation ''{0}'' definiert keine Klasse.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getMissingSpecificationMessage missingSpecificationMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Specification ''{0}'' not found.</code></pre><hr/><pre><code>Spezifikation ''{0}'' nicht gefunden.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getModulesReportMessage modulesReportMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Modules report:</code></pre><hr/><pre><code>Modulbericht:</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getScopeInfoMessage scopeInfoMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Registered ''{1}'' scope implementation ''{0}'' for ''{2}''.</code></pre><hr/><pre><code>''{1}''-G&uuml;ltigkeitsbereich-Implementierung ''{0}'' f&uuml;r ''{2}'' registriert.</code></pre></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getUnexpectedDependencyObjectsMessage unexpectedDependencyObjectsMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>Unexpected number of objects for dependency ''{1}'' of implementation ''{0}''. Expected ''{2,number}'' - found ''{3,number}''.</code></pre><hr/><pre><code>Unerwartete Anzahl Objekte f&uuml;r ''{1}''-Anforderung der Implementierung ''{0}''. Erwartet ''{2,number}'' - gefunden ''{3,number}''.</code></pre></td>
 *     </tr>
 *   </table>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultObjectManager implements ObjectManager
{
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultObjectManager} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public DefaultObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[ObjectManager]

    public <T> T getObject( final Class<T> specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        try
        {
            this.initialize();

            Class<?> specificationClass = specification;
            if ( specification.isArray() )
            {
                specificationClass = specification.getComponentType();
            }

            final ClassLoader classLoader = this.getDefaultClassLoader( specificationClass );
            final Modules model = this.getModules( classLoader );
            final Specification s = model.getSpecification( specificationClass );

            if ( s == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                        Locale.getDefault(), specificationClass.getName() ), null );

                }

                return null;
            }

            if ( s.getMultiplicity() == Multiplicity.ONE && specification.isArray() )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getIllegalArraySpecificationMessage(
                        Locale.getDefault(), s.getIdentifier(), s.getMultiplicity().value() ), null );

                }

                return null;
            }

            if ( s.getMultiplicity() != Multiplicity.ONE && !specification.isArray() )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getIllegalObjectSpecificationMessage(
                        Locale.getDefault(), s.getIdentifier(), s.getMultiplicity().value() ), null );

                }

                return null;
            }

            Scope scope = null;
            if ( s.getScope() != null )
            {
                scope = this.getScope( s.getScope(), classLoader );

                if ( scope == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingScopeMessage(
                            Locale.getDefault(), s.getScope() ), null );

                    }

                    return null;
                }
            }

            final Implementations available = model.getImplementations( s.getIdentifier() );
            if ( available == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingImplementationsMessage(
                        Locale.getDefault(), s.getIdentifier() ), null );

                }

                return null;
            }

            final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

            for ( Implementation i : available.getImplementation() )
            {
                if ( i.getLocation() != null )
                {
                    if ( s.getClazz() == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingSpecificationClassMessage(
                                Locale.getDefault(), s.getIdentifier() ), null );

                        }

                        return null;
                    }

                    final Object o = this.getObject( Class.forName( s.getClazz(), true, classLoader ),
                                                     i.getLocationUri(), classLoader );

                    if ( o == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                        }
                    }
                    else if ( specificationClass.isInstance( o ) )
                    {
                        list.add( o );
                    }
                }
                else if ( !i.isAbstract() )
                {
                    final Instance instance = model.getInstance( i.getIdentifier() );
                    if ( instance == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                        }

                        return null;
                    }

                    final Object o = this.getObject( scope, instance, classLoader );
                    if ( o == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                        }
                    }
                    else if ( specificationClass.isInstance( o ) )
                    {
                        list.add( o );
                    }
                }
            }

            if ( specification.isArray() )
            {
                @SuppressWarnings( "unchecked" )
                final T array = (T) list.toArray( (Object[]) Array.newInstance( specificationClass, list.size() ) );
                return array;
            }
            else if ( list.size() == 1 )
            {
                @SuppressWarnings( "unchecked" )
                final T object = (T) list.get( 0 );
                return object;
            }

            return null;
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( getMessage( e ), e );
        }
    }

    public <T> T getObject( final Class<T> specification, final String implementationName )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( implementationName == null )
        {
            throw new NullPointerException( "implementationName" );
        }

        try
        {
            this.initialize();

            final ClassLoader classLoader = this.getDefaultClassLoader( specification );
            final Modules model = this.getModules( classLoader );
            final Specification s = model.getSpecification( specification );

            if ( s == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                        Locale.getDefault(), specification.getName() ), null );

                }

                return null;
            }

            Scope scope = null;
            if ( s.getScope() != null )
            {
                scope = this.getScope( s.getScope(), classLoader );

                if ( scope == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingScopeMessage(
                            Locale.getDefault(), s.getScope() ), null );

                    }

                    return null;
                }
            }

            final Implementations available = model.getImplementations( s.getIdentifier() );
            if ( available == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingImplementationsMessage(
                        Locale.getDefault(), specification.getName() ), null );

                }

                return null;
            }

            final Implementation i = available.getImplementationByName( implementationName );
            if ( i == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingImplementationMessage(
                        Locale.getDefault(), s.getIdentifier(), implementationName ), null );

                }

                return null;
            }

            if ( i.getLocation() != null )
            {
                if ( s.getClazz() == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingSpecificationClassMessage(
                            Locale.getDefault(), s.getIdentifier() ), null );

                    }

                    return null;
                }

                final T object = this.getObject( Class.forName( s.getClazz(), true, classLoader ).
                    asSubclass( specification ), i.getLocationUri(), classLoader );

                if ( object == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                            Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                    }

                    return null;
                }

                return object;
            }
            else if ( !i.isAbstract() )
            {
                final Instance instance = model.getInstance( i.getIdentifier() );
                if ( instance == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                            Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                    }

                    return null;
                }

                final Object object = this.getObject( scope, instance, classLoader );
                if ( object == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                            Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                    }

                    return null;
                }
                else if ( specification.isInstance( object ) )
                {
                    @SuppressWarnings( "unchecked" )
                    final T o = (T) object;
                    return o;
                }
            }

            return null;
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( getMessage( e ), e );
        }
    }

    public Object getDependency( final Object object, final String dependencyName )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( dependencyName == null )
        {
            throw new NullPointerException( "dependencyName" );
        }

        try
        {
            this.initialize();

            final ClassLoader classLoader = this.getDefaultClassLoader( object.getClass() );
            final Modules model = this.getModules( classLoader );
            final Instance instance = model.getInstance( object );

            if ( instance == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingObjectInstanceMessage(
                        Locale.getDefault(), this.getObjectInfo( object ) ), null );

                }

                return null;
            }

            synchronized ( instance )
            {
                final Dependency dependency = instance.getDependencies() != null
                                              ? instance.getDependencies().getDependency( dependencyName ) : null;

                if ( dependency == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingDependencyMessage(
                            Locale.getDefault(), instance.getIdentifier(), dependencyName ), null );

                    }

                    return null;
                }

                Object o = instance.getDependencyObjects().get( dependencyName );
                if ( o == null )
                {
                    final Specification ds = model.getSpecification( dependency.getIdentifier() );
                    if ( ds == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                                Locale.getDefault(), dependency.getIdentifier() ), null );

                        }

                        return null;
                    }

                    Scope scope = null;
                    if ( ds.getScope() != null )
                    {
                        scope = this.getScope( ds.getScope(), classLoader );

                        if ( scope == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( classLoader, Level.WARNING, getMissingScopeMessage(
                                    Locale.getDefault(), ds.getScope() ), null );

                            }

                            return null;
                        }
                    }

                    final Implementations available = model.getImplementations( ds.getIdentifier() );
                    if ( available == null )
                    {
                        if ( !dependency.isOptional() && this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingImplementationsMessage(
                                Locale.getDefault(), dependency.getIdentifier() ), null );

                        }

                        return null;
                    }

                    if ( dependency.getImplementationName() != null )
                    {
                        final Implementation i =
                            available.getImplementationByName( dependency.getImplementationName() );

                        if ( i == null )
                        {
                            if ( !dependency.isOptional() && this.isLoggable( Level.WARNING ) )
                            {
                                this.log( classLoader, Level.WARNING, getMissingImplementationMessage(
                                    Locale.getDefault(), dependency.getIdentifier(),
                                    dependency.getImplementationName() ), null );

                            }

                            return null;
                        }

                        if ( i.getLocation() != null )
                        {
                            if ( ds.getClazz() == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( classLoader, Level.WARNING, getMissingSpecificationClassMessage(
                                        Locale.getDefault(), ds.getIdentifier() ), null );

                                }

                                return null;
                            }

                            o = this.getObject(
                                Class.forName( ds.getClazz(), true, classLoader ), i.getLocationUri(), classLoader );

                            if ( o == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                        Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                                }

                                return null;
                            }
                        }
                        else if ( !i.isAbstract() )
                        {
                            final Instance di = model.getInstance( i.getIdentifier(), dependency );
                            if ( di == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                        Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                                }

                                return null;
                            }

                            o = this.getObject( scope, di, classLoader );
                            if ( o == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                        Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                                }

                                return null;
                            }
                        }
                    }
                    else if ( ds.getMultiplicity() == Multiplicity.ONE )
                    {
                        if ( available.getImplementation().size() == 1 )
                        {
                            final Implementation ref = available.getImplementation().get( 0 );

                            if ( ref.getLocation() != null )
                            {
                                if ( ds.getClazz() == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingSpecificationClassMessage(
                                            Locale.getDefault(), ds.getIdentifier() ), null );

                                    }

                                    return null;
                                }

                                o = this.getObject( Class.forName( ds.getClazz(), true, classLoader ),
                                                    ref.getLocationUri(), classLoader );

                                if ( o == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                            Locale.getDefault(), ref.getIdentifier(), ref.getName() ), null );

                                    }

                                    return null;
                                }
                            }
                            else if ( !ref.isAbstract() )
                            {
                                final Instance di = model.getInstance( ref.getIdentifier(), dependency );
                                if ( di == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                            Locale.getDefault(), ref.getIdentifier(), ref.getName() ), null );

                                    }

                                    return null;
                                }

                                o = this.getObject( scope, di, classLoader );
                                if ( o == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                            Locale.getDefault(), ref.getIdentifier(), ref.getName() ), null );

                                    }

                                    return null;
                                }
                            }
                        }
                        else
                        {
                            this.log( classLoader, Level.WARNING, getUnexpectedDependencyObjectsMessage(
                                Locale.getDefault(), instance.getIdentifier(), dependencyName, BigInteger.ONE,
                                available.getImplementation().size() ), null );

                        }
                    }
                    else
                    {
                        final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                        if ( !available.getImplementation().isEmpty() && ds.getClazz() == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( classLoader, Level.WARNING, getMissingSpecificationClassMessage(
                                    Locale.getDefault(), ds.getIdentifier() ), null );

                            }

                            return null;
                        }

                        for ( Implementation a : available.getImplementation() )
                        {
                            if ( a.getLocation() != null )
                            {
                                final Object o2 = this.getObject( Class.forName( ds.getClazz(), true, classLoader ),
                                                                  a.getLocationUri(), classLoader );

                                if ( o2 == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                            Locale.getDefault(), a.getIdentifier(), a.getName() ), null );

                                    }
                                }
                                else
                                {
                                    list.add( o2 );
                                }
                            }
                            else if ( !a.isAbstract() )
                            {
                                final Instance di = model.getInstance( a.getIdentifier(), dependency );
                                if ( di == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                            Locale.getDefault(), a.getIdentifier(), a.getName() ), null );

                                    }

                                    return null;
                                }

                                final Object o2 = this.getObject( scope, di, classLoader );
                                if ( o2 == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingObjectMessage(
                                            Locale.getDefault(), a.getIdentifier(), a.getName() ), null );

                                    }
                                }
                                else
                                {
                                    list.add( o2 );
                                }
                            }
                        }

                        o = list.isEmpty() ? null : list.toArray( (Object[]) Array.newInstance( Class.forName(
                            ds.getClazz(), true, classLoader ), list.size() ) );

                    }
                }

                if ( o != null && dependency.isBound() )
                {
                    instance.getDependencyObjects().put( dependencyName, o );
                }

                return o;
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( getMessage( e ), e );
        }
    }

    public Object getProperty( final Object object, final String propertyName )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( propertyName == null )
        {
            throw new NullPointerException( "propertyName" );
        }

        try
        {
            this.initialize();

            final ClassLoader classLoader = this.getDefaultClassLoader( object.getClass() );
            final Modules model = this.getModules( classLoader );
            final Instance instance = model.getInstance( object );

            if ( instance == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingObjectInstanceMessage(
                        Locale.getDefault(), this.getObjectInfo( object ) ), null );

                }

                return null;
            }

            synchronized ( instance )
            {
                Object value = instance.getPropertyObjects().get( propertyName );
                if ( value == null )
                {
                    final Property property =
                        instance.getProperties() != null ? instance.getProperties().getProperty( propertyName ) : null;

                    if ( property == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingPropertyMessage(
                                Locale.getDefault(), instance.getIdentifier(), propertyName ), null );

                        }

                        return null;
                    }

                    value = property.getJavaValue( classLoader );
                    if ( value != null )
                    {
                        instance.getPropertyObjects().put( propertyName, value );
                    }
                }

                return value;
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( getMessage( e ), e );
        }
    }

    public String getMessage( final Object object, final String messageName, final Locale locale,
                              final Object... arguments )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( messageName == null )
        {
            throw new NullPointerException( "messageName" );
        }
        if ( locale == null )
        {
            throw new NullPointerException( "locale" );
        }

        try
        {
            this.initialize();

            final ClassLoader classLoader = this.getDefaultClassLoader( object.getClass() );
            final Modules model = this.getModules( classLoader );
            final Instance instance = model.getInstance( object );

            if ( instance == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingObjectInstanceMessage(
                        Locale.getDefault(), this.getObjectInfo( object ) ), null );

                }

                return null;
            }

            synchronized ( instance )
            {
                final Message message =
                    instance.getMessages() != null ? instance.getMessages().getMessage( messageName ) : null;

                if ( message == null || message.getTemplate() == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingMessageMessage(
                            Locale.getDefault(), instance.getIdentifier(), messageName ), null );

                    }

                    return null;
                }

                final String text = MessageFormat.format( message.getTemplate().getText(
                    locale.getLanguage().toLowerCase( Locale.ENGLISH ) ).getValue(), arguments );

                final StringBuilder builder = new StringBuilder( text.length() );
                final BufferedReader reader = new BufferedReader( new StringReader( text ) );

                String line;
                while ( ( line = reader.readLine() ) != null )
                {
                    builder.append( LINE_SEPARATOR ).append( line );
                }

                return builder.substring( LINE_SEPARATOR.length() );
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( getMessage( e ), e );
        }
    }

    // SECTION-END
    // SECTION-START[DefaultObjectManager]
    /** Constant for the {@code Singleton} scope identifier. */
    protected static final String SINGLETON_SCOPE_IDENTIFIER = "Singleton";

    /** System's line separator. */
    private static final String LINE_SEPARATOR = System.getProperty( "line.separator", "\n" );

    /**
     * Log level events are logged at by default.
     * @see #getDefaultLogLevel()
     */
    private static final Level DEFAULT_LOG_LEVEL = Level.WARNING;

    /** Default log level. */
    private static volatile Level defaultLogLevel;

    /** Name of the platform's bootstrap class loader class. */
    private static volatile String bootstrapClassLoaderClassName;

    private static volatile boolean bootstrapClassLoaderClassNameInitialized;

    /**
     * Identifier of the model to search for modules by default.
     * @since 1.1
     */
    private static volatile String defaultModelIdentifier;

    /**
     * Identifier of the model to search for modules.
     * @since 1.1
     */
    private String modelIdentifier;

    /**
     * Flag indicating model object classpath resolution is enabled by default.
     * @since 1.1
     */
    private static volatile Boolean defaultModelObjectClasspathResolutionEnabled;

    /**
     * Flag indicating model object classpath resolution is enabled.
     * @since 1.1
     */
    private Boolean modelObjectClasspathResolutionEnabled;

    /**
     * Flag indicating model processing is enabled by default.
     * @since 1.1
     */
    private static volatile Boolean defaultModelProcessingEnabled;

    /**
     * Flag indicating model processing is enabled.
     * @since 1.1
     */
    private Boolean modelProcessingEnabled;

    /** {@code ClassLoader} instance representing the bootstrap class loader. */
    private static final ClassLoader BOOTSTRAP_CLASSLOADER = new ClassLoader( null )
    {

        @Override
        public String toString()
        {
            return DefaultObjectManager.class.getName() + ".BootstrapClassLoader@"
                   + Integer.toHexString( this.hashCode() );

        }

    };

    /** Flag indicating that initialization has been performed. */
    private boolean initialized;

    /** Log level of the instance. */
    private Level logLevel;

    /** Listeners of the instance. */
    private final Map<ClassLoader, List<Listener>> listeners = new WeakIdentityHashMap();

    /** Modules of the instance. */
    private final Map<ClassLoader, Modules> modules = new WeakIdentityHashMap();

    /** Invokers of the instance. */
    private final Map<ClassLoader, Invoker> invokers = new WeakIdentityHashMap();

    /** Scopes of the instance. */
    private final Map<ClassLoader, Map<String, Scope>> scopes = new WeakIdentityHashMap();

    /** Locators of the instance. */
    private final Map<ClassLoader, Map<String, Locator>> locators = new WeakIdentityHashMap();

    /** Objects of the instance. */
    private final Map<ClassLoader, Map<Object, Instance>> objects = new WeakIdentityHashMap();

    /** {@code ObjectManager} singletons. */
    private static final Map<ClassLoader, ObjectManager> singletons = new WeakIdentityHashMap();

    /**
     * Default {@link ObjectManagerFactory#getObjectManager(ClassLoader)} implementation.
     *
     * @param classLoader The class loader to use for getting the singleton instance; {@code null} to use the platform's
     * bootstrap class loader.
     *
     * @return The default {@code ObjectManager} singleton instance.
     *
     * @see ObjectManagerFactory#getObjectManager(ClassLoader)
     */
    public static ObjectManager getObjectManager( final ClassLoader classLoader )
    {
        synchronized ( singletons )
        {
            final ClassLoader singletonsLoader = getClassLoader( classLoader );
            ObjectManager manager = singletons.get( singletonsLoader );
            if ( manager == null )
            {
                manager = ObjectManagerFactory.newObjectManager( classLoader );
                singletons.put( singletonsLoader, manager );
            }

            return manager.getObject( ObjectManager.class );
        }
    }

    /**
     * Gets the list of listeners registered with the class loader of the instance.
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make
     * to the returned list will be present inside the object. This is why there is no {@code set} method for the
     * listeners property.</p>
     * <p>Calling this method is the same as calling<blockquote><pre>
     * getListeners( getClassLoader( getClass() ) );</pre></blockquote>
     *
     * @return The list of registered listeners.
     *
     * @see #getListeners(java.lang.ClassLoader)
     */
    public List<Listener> getListeners()
    {
        return this.getListeners( this.getDefaultClassLoader( this.getClass() ) );
    }

    /**
     * Gets the list of listeners registered with a given class loader.
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make
     * to the returned list will be present inside the object. This is why there is no {@code set} method for the
     * listeners property.</p>
     *
     * @param classLoader The class loader to get registered listeners of.
     *
     * @return The list of listeners registered with {@code classLoader}.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     *
     * @see #getDefaultListener()
     *
     * @since 1.1
     */
    public List<Listener> getListeners( final ClassLoader classLoader )
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        final ClassLoader listenersLoader = this.getDefaultClassLoader( classLoader );

        synchronized ( this.listeners )
        {
            List<Listener> cachedListeners = this.listeners.get( listenersLoader );

            if ( cachedListeners == null )
            {
                final List<LogRecord> bootstrapRecords = new ArrayList<LogRecord>( 1024 );
                final Listener bootstrapListener = new Listener()
                {

                    public void onLog( final Level level, final String message, final Throwable throwable )
                    {
                        final LogRecord r = new LogRecord( level, message );
                        r.setThrown( throwable );

                        bootstrapRecords.add( r );
                    }

                };

                cachedListeners = new LinkedList<Listener>();
                cachedListeners.add( bootstrapListener );
                this.listeners.put( listenersLoader, cachedListeners );

                final List<Listener> modelListeners = new LinkedList<Listener>();
                final Modules model = this.getModules( classLoader );
                final Specification listenerSpecification = model.getSpecification( Listener.class );

                if ( listenerSpecification != null )
                {
                    final Implementations implementations =
                        model.getImplementations( listenerSpecification.getIdentifier() );

                    if ( implementations != null && !implementations.getImplementation().isEmpty() )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            final Instance listenerInstance = model.getInstance( i.getIdentifier() );
                            if ( listenerInstance != null )
                            {
                                try
                                {
                                    final Listener l = (Listener) model.createObject( listenerInstance, classLoader );
                                    modelListeners.add( l );

                                    if ( this.isLoggable( Level.CONFIG ) )
                                    {
                                        this.log( classLoader, Level.CONFIG, getListenerInfoMessage(
                                            Locale.getDefault(), l.getClass().getName(),
                                            this.getClassLoaderInfo( classLoader, listenersLoader ) ), null );

                                    }
                                }
                                catch ( final InstantiationException e )
                                {
                                    if ( this.isLoggable( Level.SEVERE ) )
                                    {
                                        this.log( classLoader, Level.SEVERE, getMessage( e ), e );
                                    }
                                }
                            }
                            else if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                    Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                            }
                        }
                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingImplementationsMessage(
                            Locale.getDefault(), listenerSpecification.getIdentifier() ), null );

                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                        Locale.getDefault(), Listener.class.getName() ), null );

                }

                cachedListeners.remove( bootstrapListener );
                cachedListeners.addAll( modelListeners );

                if ( cachedListeners.isEmpty() )
                {
                    if ( !classLoader.equals( this.getDefaultClassLoader( this.getClass() ) ) )
                    {
                        cachedListeners.addAll( this.getListeners() );
                    }
                    else
                    {
                        cachedListeners.add( this.getDefaultListener() );

                        if ( this.isLoggable( Level.CONFIG ) )
                        {
                            this.log( Level.CONFIG, getDefaultListenerInfo(
                                Locale.getDefault(), this.getClassLoaderInfo( classLoader, listenersLoader ) ), null );

                        }
                    }
                }

                for ( LogRecord r : bootstrapRecords )
                {
                    this.log( classLoader, r.getLevel(), r.getMessage(), r.getThrown() );
                }
            }

            return cachedListeners;
        }
    }

    /**
     * Gets a new default listener implementation instance.
     *
     * @return A new default listener implementation instance.
     *
     * @see #getListeners()
     * @see #getListeners(java.lang.ClassLoader)
     *
     * @since 1.1
     */
    public Listener getDefaultListener()
    {
        return new DefaultListener();
    }

    /**
     * Gets the default log level events are logged at.
     * <p>The default log level is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.defaultLogLevel} holding the log level to log events at by default.
     * If that property is not set, the {@code WARNING} default is returned.</p>
     *
     * @return The log level events are logged at by default.
     *
     * @see #getLogLevel()
     * @see Level#parse(java.lang.String)
     */
    public static Level getDefaultLogLevel()
    {
        if ( defaultLogLevel == null )
        {
            defaultLogLevel = Level.parse( System.getProperty( "org.jomc.ri.DefaultObjectManager.defaultLogLevel",
                                                               DEFAULT_LOG_LEVEL.getName() ) );

        }

        return defaultLogLevel;
    }

    /**
     * Sets the default log level events are logged at.
     *
     * @param value The new default level events are logged at or {@code null}.
     *
     * @see #getDefaultLogLevel()
     */
    public static void setDefaultLogLevel( final Level value )
    {
        defaultLogLevel = value;
    }

    /**
     * Gets the log level of the instance.
     *
     * @return The log level of the instance.
     *
     * @see #getDefaultLogLevel()
     * @see #setLogLevel(java.util.logging.Level)
     * @see #isLoggable(java.util.logging.Level)
     */
    public final Level getLogLevel()
    {
        if ( this.logLevel == null )
        {
            this.logLevel = getDefaultLogLevel();

            if ( this.isLoggable( Level.CONFIG ) )
            {
                this.log( Level.CONFIG, getDefaultLogLevelInfoMessage(
                    Locale.getDefault(), this.logLevel.getLocalizedName() ), null );

            }
        }

        return this.logLevel;
    }

    /**
     * Sets the log level of the instance.
     *
     * @param value The new log level of the instance or {@code null}.
     *
     * @see #getLogLevel()
     * @see #isLoggable(java.util.logging.Level)
     */
    public final void setLogLevel( final Level value )
    {
        this.logLevel = value;
    }

    /**
     * Checks if a message at a given level is provided to the listeners of the instance.
     *
     * @param level The level to test.
     *
     * @return {@code true} if messages at {@code level} are provided to the listeners of the instance;
     * {@code false} if messages at {@code level} are not provided to the listeners of the instance.
     *
     * @throws NullPointerException if {@code level} is {@code null}.
     *
     * @see #getLogLevel()
     * @see #setLogLevel(java.util.logging.Level)
     * @see #log(java.util.logging.Level, java.lang.String, java.lang.Throwable)
     * @see #log(java.lang.ClassLoader, java.util.logging.Level, java.lang.String, java.lang.Throwable)
     */
    public boolean isLoggable( final Level level )
    {
        if ( level == null )
        {
            throw new NullPointerException( "level" );
        }

        return level.intValue() >= this.getLogLevel().intValue();
    }

    /**
     * Notifies listeners registered with the class loader of the instance.
     * <p>Calling this method is the same as calling<blockquote><pre>
     * log( getClassLoader( getClass() ), level, message, throwable );</pre></blockquote></p>
     *
     * @param level The level of the event.
     * @param message The message of the event or {@code null}.
     * @param throwable The throwable of the event or {@code null}.
     *
     * @throws NullPointerException if {@code level} is {@code null}.
     *
     * @see #log(java.lang.ClassLoader, java.util.logging.Level, java.lang.String, java.lang.Throwable)
     */
    public void log( final Level level, final String message, final Throwable throwable )
    {
        this.log( this.getDefaultClassLoader( this.getClass() ), level, message, throwable );
    }

    /**
     * Notifies listeners registered with a given class loader.
     *
     * @param classLoader The class loader to notify listeners of.
     * @param level The level of the event.
     * @param message The message of the event or {@code null}.
     * @param throwable The throwable of the event or {@code null}.
     *
     * @throws NullPointerException if {@code classLoader} or {@code level} is {@code null}.
     *
     * @since 1.1
     */
    public void log( final ClassLoader classLoader, final Level level, final String message, final Throwable throwable )
    {
        if ( level == null )
        {
            throw new NullPointerException( "level" );
        }
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        if ( this.isLoggable( level ) )
        {
            for ( Listener l : this.getListeners( classLoader ) )
            {
                l.onLog( level, message, throwable );
            }
        }
    }

    /**
     * Gets the identifier of the model to search for modules by default.
     * <p>The identifier of the model to search for modules by default is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.defaultModelIdentifier} holding the identifier of the model to search for
     * modules by default. If that property is not set, the {@code http://jomc.org/model} default is returned.</p>
     *
     * @return The identifier of the model to search for modules by default.
     *
     * @see #getModelIdentifier()
     * @see #setDefaultModelIdentifier(java.lang.String)
     * @see ModelObject#MODEL_PUBLIC_ID
     *
     * @since 1.1
     */
    public static String getDefaultModelIdentifier()
    {
        if ( defaultModelIdentifier == null )
        {
            defaultModelIdentifier = System.getProperty( "org.jomc.ri.DefaultObjectManager.defaultModelIdentifier",
                                                         ModelObject.MODEL_PUBLIC_ID );

        }

        return defaultModelIdentifier;
    }

    /**
     * Sets the identifier of the model to search for modules by default.
     *
     * @param value The new identifier of the model to search for modules by default or {@code null}.
     *
     * @see #getDefaultModelIdentifier()
     *
     * @since 1.1
     */
    public static void setDefaultModelIdentifier( final String value )
    {
        defaultModelIdentifier = value;
    }

    /**
     * Gets the identifier of the model to search for modules.
     *
     * @return The identifier of the model to search for modules.
     *
     * @see #getDefaultModelIdentifier()
     * @see #setModelIdentifier(java.lang.String)
     *
     * @since 1.1
     */
    public final String getModelIdentifier()
    {
        if ( this.modelIdentifier == null )
        {
            this.modelIdentifier = getDefaultModelIdentifier();

            if ( this.isLoggable( Level.CONFIG ) )
            {
                this.log( Level.CONFIG, getDefaultModelIdentifierInfo(
                    Locale.getDefault(), this.modelIdentifier ), null );

            }
        }

        return this.modelIdentifier;
    }

    /**
     * Sets the identifier of the model to search for modules.
     *
     * @param value The new identifier of the model to search for modules or {@code null}.
     *
     * @since 1.1
     */
    public final void setModelIdentifier( final String value )
    {
        this.modelIdentifier = value;
    }

    /**
     * Gets a flag indicating model object classpath resolution is enabled by default.
     * <p>The default model object classpath resolution enabled flag is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.defaultModelObjectClasspathResolutionEnabled} holding a boolean
     * indicating model object classpath resolution is enabled by default. If that property is not set, the
     * {@code true} default is returned.</p>
     *
     * @return {@code true} if model object classpath resolution is enabled by default; {@code false} if model object
     * classpath resolution is disabled by default.
     *
     * @see #isModelObjectClasspathResolutionEnabled()
     * @see #setDefaultModelObjectClasspathResolutionEnabled(java.lang.Boolean)
     *
     * @since 1.1
     */
    public static boolean isDefaultModelObjectClasspathResolutionEnabled()
    {
        if ( defaultModelObjectClasspathResolutionEnabled == null )
        {
            defaultModelObjectClasspathResolutionEnabled = Boolean.valueOf( System.getProperty(
                "org.jomc.ri.DefaultObjectManager.defaultModelObjectClasspathResolutionEnabled",
                Boolean.toString( true ) ) );

        }

        return defaultModelObjectClasspathResolutionEnabled;
    }

    /**
     * Sets the flag indicating model object classpath resolution is enabled by default.
     *
     * @param value The new value of the flag indicating model object classpath resolution is enabled by default or
     * {@code null}.
     *
     * @see #isDefaultModelObjectClasspathResolutionEnabled()
     *
     * @since 1.1
     */
    public static void setDefaultModelObjectClasspathResolutionEnabled( final Boolean value )
    {
        defaultModelObjectClasspathResolutionEnabled = value;
    }

    /**
     * Gets a flag indicating model object classpath resolution is enabled.
     *
     * @return {@code true} if model object classpath resolution is enabled; {@code false} if model object classpath
     * resolution is disabled.
     *
     * @see #isDefaultModelObjectClasspathResolutionEnabled()
     * @see #setModelObjectClasspathResolutionEnabled(java.lang.Boolean)
     *
     * @since 1.1
     */
    public final boolean isModelObjectClasspathResolutionEnabled()
    {
        if ( this.modelObjectClasspathResolutionEnabled == null )
        {
            this.modelObjectClasspathResolutionEnabled = isDefaultModelObjectClasspathResolutionEnabled();

            if ( this.isLoggable( Level.CONFIG ) )
            {
                this.log( Level.CONFIG, getDefaultModelObjectClasspahResolutionEnabledInfo(
                    Locale.getDefault(), Boolean.toString( this.modelObjectClasspathResolutionEnabled ) ), null );

            }
        }

        return this.modelObjectClasspathResolutionEnabled;
    }

    /**
     * Sets the flag indicating model object classpath resolution is enabled.
     *
     * @param value The new value of the flag indicating model object classpath resolution is enabled or {@code null}.
     *
     * @see #isModelObjectClasspathResolutionEnabled()
     *
     * @since 1.1
     */
    public final void setModelObjectClasspathResolutionEnabled( final Boolean value )
    {
        this.modelObjectClasspathResolutionEnabled = value;
    }

    /**
     * Gets a flag indicating model processing is enabled by default.
     * <p>The default model processing enabled flag is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.defaultModelProcessingEnabled} holding a boolean indicating model
     * processing is enabled by default. If that property is not set, the {@code true} default is returned.</p>
     *
     * @return {@code true} if model processing is enabled by default; {@code false} if model processing is disabled by
     * default.
     *
     * @see #isModelProcessingEnabled()
     * @see #setDefaultModelProcessingEnabled(java.lang.Boolean)
     *
     * @since 1.1
     */
    public static boolean isDefaultModelProcessingEnabled()
    {
        if ( defaultModelProcessingEnabled == null )
        {
            defaultModelProcessingEnabled = Boolean.valueOf( System.getProperty(
                "org.jomc.ri.DefaultObjectManager.defaultModelProcessingEnabled", Boolean.toString( true ) ) );

        }

        return defaultModelProcessingEnabled;
    }

    /**
     * Sets the flag indicating model processing is enabled by default.
     *
     * @param value The new value of the flag indicating model processing is enabled by default or {@code null}.
     *
     * @see #isDefaultModelProcessingEnabled()
     *
     * @since 1.1
     */
    public static void setDefaultModelProcessingEnabled( final Boolean value )
    {
        defaultModelProcessingEnabled = value;
    }

    /**
     * Gets a flag indicating model processing is enabled.
     *
     * @return {@code true} if model processing is enabled; {@code false} if model processing is disabled .
     *
     * @see #isDefaultModelProcessingEnabled()
     * @see #setModelProcessingEnabled(java.lang.Boolean)
     *
     * @since 1.1
     */
    public final boolean isModelProcessingEnabled()
    {
        if ( this.modelProcessingEnabled == null )
        {
            this.modelProcessingEnabled = isDefaultModelProcessingEnabled();

            if ( this.isLoggable( Level.CONFIG ) )
            {
                this.log( Level.CONFIG, getDefaultModelProcessingEnabledInfo(
                    Locale.getDefault(), Boolean.toString( this.modelProcessingEnabled ) ), null );

            }
        }

        return this.modelProcessingEnabled;
    }

    /**
     * Sets the flag indicating model processing is enabled.
     *
     * @param value The new value of the flag indicating model processing is enabled or {@code null}.
     *
     * @see #isModelProcessingEnabled()
     *
     * @since 1.1
     */
    public final void setModelProcessingEnabled( final Boolean value )
    {
        this.modelProcessingEnabled = value;
    }

    /**
     * Gets the name of the platform's bootstrap class loader class.
     * <p>The name of the platform's bootstrap class loader class is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.bootstrapClassLoaderClassName} holding the name of the platform's
     * bootstrap class loader class. If that property is not set, the bootstrap class loader is assumed to be
     * represented by a {@code null} parent class loader.</p>
     *
     * @return The name of the platform's bootstrap class loader class or {@code null}.
     *
     * @see #getClassLoader(java.lang.ClassLoader)
     */
    public static String getBootstrapClassLoaderClassName()
    {
        if ( bootstrapClassLoaderClassName == null && !bootstrapClassLoaderClassNameInitialized )
        {
            bootstrapClassLoaderClassName =
                System.getProperty( "org.jomc.ri.DefaultObjectManager.bootstrapClassLoaderClassName" );

            bootstrapClassLoaderClassNameInitialized = true;
        }

        return bootstrapClassLoaderClassName;
    }

    /**
     * Sets the name of the platform's bootstrap class loader class.
     *
     * @param value The new name of the platform's bootstrap class loader class or {@code null}.
     *
     * @see #getBootstrapClassLoaderClassName()
     */
    public static void setBootstrapClassLoaderClassName( final String value )
    {
        bootstrapClassLoaderClassName = value;
        bootstrapClassLoaderClassNameInitialized = false;
    }

    /**
     * Gets the modules of a given class loader.
     *
     * @param classLoader The class loader to get the modules of.
     *
     * @return The modules of the given class loader.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     *
     * @see #getDefaultModules()
     * @see #getModelIdentifier()
     * @see #isModelObjectClasspathResolutionEnabled()
     * @see #isModelProcessingEnabled()
     */
    public Modules getModules( final ClassLoader classLoader )
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        synchronized ( this.modules )
        {
            Modules cachedModules = this.modules.get( classLoader );

            if ( cachedModules == null )
            {
                final List<LogRecord> logRecords = new ArrayList<LogRecord>( 1024 );

                try
                {
                    final ModelContext modelContext = ModelContext.createModelContext( classLoader );

                    logRecords.add( new LogRecord( Level.FINER, getCreatingModulesInfo(
                        Locale.getDefault(), this.getClassLoaderInfo( classLoader, null ) ) ) );

                    modelContext.setLogLevel( this.getLogLevel() );
                    modelContext.getListeners().add( new ModelContext.Listener()
                    {

                        public void onLog( final Level level, final String message, final Throwable t )
                        {
                            final LogRecord r = new LogRecord( level, message );
                            r.setThrown( t );

                            logRecords.add( r );
                        }

                    } );

                    Model model = modelContext.findModel( this.getModelIdentifier() );
                    cachedModules = ModelHelper.getModules( model );

                    if ( cachedModules != null )
                    {
                        if ( this.isModelObjectClasspathResolutionEnabled() )
                        {
                            final Module classpathModule = cachedModules.getClasspathModule(
                                Modules.getDefaultClasspathModuleName(), classLoader );

                            if ( classpathModule != null )
                            {
                                cachedModules.getModule().add( classpathModule );
                            }
                        }

                        if ( this.isModelProcessingEnabled() )
                        {
                            model = modelContext.processModel( model );
                        }

                        final ModelValidationReport validationReport = modelContext.validateModel( model );

                        for ( ModelValidationReport.Detail d : validationReport.getDetails() )
                        {
                            final LogRecord r = new LogRecord( d.getLevel(), d.getMessage() );
                            logRecords.add( r );
                        }

                        cachedModules = validationReport.isModelValid() ? ModelHelper.getModules( model ) : null;
                    }
                }
                catch ( final ModelException e )
                {
                    cachedModules = null;

                    final LogRecord r = new LogRecord( Level.SEVERE, getMessage( e ) );
                    r.setThrown( e );
                    logRecords.add( r );
                }

                if ( cachedModules == null )
                {
                    cachedModules = this.getDefaultModules();

                    logRecords.add( new LogRecord( Level.WARNING, getDefaultModulesWarning(
                        Locale.getDefault(), this.getModelIdentifier(),
                        this.getClassLoaderInfo( classLoader, null ) ) ) );

                }

                final ClassLoader objectsLoader = this.getDefaultClassLoader( classLoader );

                synchronized ( this.objects )
                {
                    Map<Object, Instance> objectMap = this.objects.get( objectsLoader );
                    if ( objectMap == null )
                    {
                        objectMap = new WeakIdentityHashMap();
                        this.objects.put( objectsLoader, objectMap );
                    }

                    cachedModules = new Modules( cachedModules, objectMap );
                }

                this.modules.put( classLoader, cachedModules );

                for ( LogRecord r : logRecords )
                {
                    this.log( classLoader, r.getLevel(), r.getMessage(), r.getThrown() );
                }

                if ( this.isLoggable( Level.FINEST ) )
                {
                    this.logModulesReport( cachedModules, classLoader );
                }
            }

            return cachedModules;
        }
    }

    /**
     * Gets a new default modules instance.
     *
     * @return A new default modules instance.
     *
     * @see #getModules(java.lang.ClassLoader)
     *
     * @since 1.1
     */
    public Modules getDefaultModules()
    {
        final Modules defaultModules = new Modules();
        final Module defaultModule = new Module();
        defaultModule.setSpecifications( new Specifications() );
        defaultModule.setImplementations( new Implementations() );
        defaultModules.getModule().add( defaultModule );
        defaultModule.setName( getDefaultModuleName( Locale.getDefault() ) );

        defaultModule.getSpecifications().getSpecification().add( createDefaultSpecification(
            ObjectManager.class, Multiplicity.ONE, SINGLETON_SCOPE_IDENTIFIER ) );

        defaultModule.getSpecifications().getSpecification().add( createDefaultSpecification(
            Scope.class, null, null ) );

        defaultModule.getSpecifications().getSpecification().add( createDefaultSpecification(
            Listener.class, null, null ) );

        defaultModule.getSpecifications().getSpecification().add( createDefaultSpecification(
            Locator.class, null, null ) );

        defaultModule.getSpecifications().getSpecification().add( createDefaultSpecification(
            Invoker.class, null, null ) );

        defaultModule.getSpecifications().getSpecification().add( createDefaultSpecification(
            Invocation.class, Multiplicity.ONE, null ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            ObjectManagerFactory.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            ObjectManagementException.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            DefaultInvocation.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            DefaultInvoker.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            DefaultListener.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            DefaultLocator.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        defaultModule.getImplementations().getImplementation().add( createDefaultImplementation(
            DefaultScope.class, getDefaultImplementationName( Locale.getDefault() ) ) );

        final Implementation defaultObjectManager = createDefaultImplementation(
            DefaultObjectManager.class, getDefaultImplementationName( Locale.getDefault() ) );

        defaultObjectManager.setSpecifications( new Specifications() );

        final SpecificationReference refObjectManager = new SpecificationReference();
        refObjectManager.setIdentifier( ObjectManager.class.getName() );
        refObjectManager.setVersion( getDefaultModulesVersion( Locale.getDefault() ) );
        defaultObjectManager.getSpecifications().getReference().add( refObjectManager );

        defaultModule.getImplementations().getImplementation().add( defaultObjectManager );
        return defaultModules;
    }

    /**
     * Gets the class loader of a given class.
     *
     * @param clazz The class whose class loader to return.
     *
     * @return The class loader of {@code clazz}.
     *
     * @throws NullPointerException if {@code clazz} is {@code null}.
     *
     * @since 1.1
     */
    public ClassLoader getDefaultClassLoader( final Class<?> clazz )
    {
        if ( clazz == null )
        {
            throw new NullPointerException( "clazz" );
        }

        ClassLoader cl = clazz.getClassLoader();
        if ( cl == null )
        {
            cl = BOOTSTRAP_CLASSLOADER;
        }

        return cl;
    }

    /**
     * Gets the parent class loader of a given class loader recursively.
     * <p>This method recursively finds the parent class loader of the given class loader. Recursion stops at the
     * platform's bootstrap class loader. That class loader is detected when either the current class loader has no
     * parent (a call to the {@code getParent()} method returns {@code null}) or when the class name of the
     * current class loader's parent class loader is equal to the name returned by method
     * {@code getBootstrapClassLoaderClassName()}. Configuration of the name of the platform's bootstrap class loader
     * class is needed when the platform's {@code getParent()} method of the {@code ClassLoader} class does not return
     * {@code null} to indicate the bootstrap class loader but instead returns an instance of {@code ClassLoader}.</p>
     *
     * @param classLoader The class loader whose parent class loader to return or {@code null} to return a
     * {@code ClassLoader} instance representing the platform's bootstrap class loader.
     *
     * @return The parent class loader of {@code classLoader}.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     *
     * @see #getBootstrapClassLoaderClassName()
     * @see ClassLoader#getParent()
     *
     * @since 1.1
     */
    public ClassLoader getDefaultClassLoader( final ClassLoader classLoader )
    {
        if ( classLoader == null )
        {
            return BOOTSTRAP_CLASSLOADER;
        }

        if ( classLoader.getParent() != null
             && !classLoader.getParent().getClass().getName().equals( getBootstrapClassLoaderClassName() ) )
        {
            return this.getDefaultClassLoader( classLoader.getParent() );
        }

        return classLoader;
    }

    /**
     * Gets the class loader of a given class.
     *
     * @param clazz The class whose class loader to return.
     *
     * @return The class loader of {@code clazz}.
     *
     * @throws NullPointerException if {@code clazz} is {@code null}.
     *
     * @deprecated As of JOMC 1.1, please use method {@link #getDefaultClassLoader(java.lang.Class)}.
     */
    @Deprecated
    public static ClassLoader getClassLoader( final Class<?> clazz )
    {
        if ( clazz == null )
        {
            throw new NullPointerException( "clazz" );
        }

        ClassLoader cl = clazz.getClassLoader();
        if ( cl == null )
        {
            cl = BOOTSTRAP_CLASSLOADER;
        }

        return cl;
    }

    /**
     * Gets the parent class loader of a given class loader recursively.
     * <p>This method recursively finds the parent class loader of the given class loader. Recursion stops at the
     * platform's bootstrap class loader. That class loader is detected when either the current class loader has no
     * parent (a call to the {@code getParent()} method returns {@code null}) or when the class name of the
     * current class loader's parent class loader is equal to the name returned by method
     * {@code getBootstrapClassLoaderClassName()}. Configuration of the name of the platform's bootstrap class loader
     * class is needed when the platform's {@code getParent()} method of the {@code ClassLoader} class does not return
     * {@code null} to indicate the bootstrap class loader but instead returns an instance of {@code ClassLoader}.</p>
     *
     * @param classLoader The class loader whose parent class loader to return or {@code null} to return a
     * {@code ClassLoader} instance representing the platform's bootstrap class loader.
     *
     * @return The parent class loader of {@code classLoader}.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     *
     * @see #getBootstrapClassLoaderClassName()
     * @see ClassLoader#getParent()
     *
     * @deprecated As of JOMC 1.1, please use method {@link #getDefaultClassLoader(java.lang.ClassLoader)}.
     */
    @Deprecated
    public static ClassLoader getClassLoader( final ClassLoader classLoader )
    {
        if ( classLoader == null )
        {
            return BOOTSTRAP_CLASSLOADER;
        }

        if ( classLoader.getParent() != null
             && !classLoader.getParent().getClass().getName().equals( getBootstrapClassLoaderClassName() ) )
        {
            return getClassLoader( classLoader.getParent() );
        }

        return classLoader;
    }

    /**
     * Gets an object of a given instance from a given scope.
     *
     * @param scope The scope to get the object from or {@code null}.
     * @param instance The instance of the object to get.
     * @param classLoader The class loader to use for creating the object.
     *
     * @return An object of {@code instance} from {@code scope} or {@code null} if no such object is found.
     *
     * @throws NullPointerException if {@code instance} or {@code classLoader} is {@code null}.
     * @throws InstantiationException if creating an object fails.
     */
    public Object getObject( final Scope scope, final Instance instance, final ClassLoader classLoader )
        throws InstantiationException
    {
        if ( instance == null )
        {
            throw new NullPointerException( "instance" );
        }
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        Object object = null;
        final Modules model = this.getModules( classLoader );

        if ( scope != null )
        {
            synchronized ( scope )
            {
                object = scope.getObject( instance.getIdentifier() );

                if ( object == null )
                {
                    scope.putObject( instance.getIdentifier(), instance );

                    try
                    {
                        object = model.createObject( instance, classLoader );
                    }
                    finally
                    {
                        if ( object != null )
                        {
                            object = this.createProxy( instance, object, classLoader );
                        }

                        scope.putObject( instance.getIdentifier(), object );
                    }
                }
                else if ( object instanceof Instance )
                {
                    throw new ObjectManagementException( getDependencyCycleMessage(
                        Locale.getDefault(), ( (Instance) object ).getIdentifier() ) );

                }
            }
        }
        else
        {
            try
            {
                object = model.createObject( instance, classLoader );
            }
            finally
            {
                if ( object != null )
                {
                    object = this.createProxy( instance, object, classLoader );
                }
            }
        }

        return object;
    }

    /**
     * Gets an object for a given location URI.
     *
     * @param specification The specification class of the object to locate.
     * @param location The location URI of the object to locate.
     * @param classLoader The class loader to use for loading locator classes.
     * @param <T> The type of the object.
     *
     * @return An object located at {@code location} or {@code null} if no such object is found.
     *
     * @throws NullPointerException if {@code specification}, {@code location} or {@code classLoader} is {@code null}.
     * @throws InstantiationException if instantiating a locator fails.
     * @throws ClassNotFoundException if the class of {@code specification} is not found.
     * @throws IOException if locating the object fails.
     */
    public <T> T getObject( final Class<T> specification, final URI location, final ClassLoader classLoader )
        throws InstantiationException, ClassNotFoundException, IOException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        T object = null;
        final Locator locator = this.getLocator( location, classLoader );

        if ( locator != null )
        {
            object = locator.getObject( specification, location );
        }
        else if ( this.isLoggable( Level.WARNING ) )
        {
            this.log( classLoader, Level.WARNING, getMissingLocatorMessage(
                Locale.getDefault(), location.getScheme() ), null );

        }

        return object;
    }

    /**
     * Gets the scope implementation for a given scope identifier.
     *
     * @param identifier The identifier of the scope to get an implementation of.
     * @param classLoader The class loader to use for loading scope implementations.
     *
     * @return The implementation of the scope identified by {@code identifier} or {@code null} if no such scope
     * implementation is found.
     *
     * @throws NullPointerException if {@code classLoader} or {@code identifier} is {@code null}.
     * @throws InstantiationException if instantiating a scope fails.
     *
     * @see #getDefaultScope(java.lang.String)
     */
    public Scope getScope( final String identifier, final ClassLoader classLoader ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }
        if ( identifier == null )
        {
            throw new NullPointerException( "identifier" );
        }

        final Modules model = this.getModules( classLoader );
        final ClassLoader scopesLoader = this.getDefaultClassLoader( classLoader );

        synchronized ( this.scopes )
        {
            Map<String, Scope> cachedScopes = this.scopes.get( scopesLoader );
            if ( cachedScopes == null )
            {
                cachedScopes = new HashMap<String, Scope>();
                this.scopes.put( scopesLoader, cachedScopes );
            }

            Scope scope = cachedScopes.get( identifier );

            if ( scope == null )
            {
                // Bootstrap scope loading.
                final Specification scopeSpecification = model.getSpecification( Scope.class );

                if ( scopeSpecification != null )
                {
                    final Implementations implementations =
                        model.getImplementations( scopeSpecification.getIdentifier() );

                    if ( implementations != null )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            if ( identifier.equals( i.getName() ) )
                            {
                                final Instance instance = model.getInstance( i.getIdentifier() );

                                if ( instance != null )
                                {
                                    scope = (Scope) model.createObject( instance, classLoader );
                                    cachedScopes.put( identifier, scope );
                                    if ( this.isLoggable( Level.CONFIG ) )
                                    {
                                        this.log( classLoader, Level.CONFIG, getScopeInfoMessage(
                                            Locale.getDefault(), i.getIdentifier(), identifier,
                                            this.getClassLoaderInfo( classLoader, scopesLoader ) ), null );

                                    }
                                    break;
                                }
                                else if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                        Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                                }
                            }
                        }
                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                        Locale.getDefault(), Scope.class.getName() ), null );

                }
            }

            if ( scope == null )
            {
                scope = this.getDefaultScope( identifier );
                if ( scope != null )
                {
                    cachedScopes.put( identifier, scope );
                    if ( this.isLoggable( Level.CONFIG ) )
                    {
                        this.log( classLoader, Level.CONFIG, getDefaultScopeInfoMessage(
                            Locale.getDefault(), identifier,
                            this.getClassLoaderInfo( classLoader, scopesLoader ) ), null );

                    }
                }
            }

            return scope;
        }
    }

    /**
     * Gets a new default scope implementation instance for a given identifier.
     *
     * @param identifier The identifier to get a new default scope implementation instance for.
     *
     * @return A new default scope implementation instance for {@code identifier} or {@code null} if no such instance is
     * available.
     *
     * @throws NullPointerException if {@code identifier} is {@code null}.
     *
     * @see #getScope(java.lang.String, java.lang.ClassLoader)
     */
    public Scope getDefaultScope( final String identifier )
    {
        if ( identifier == null )
        {
            throw new NullPointerException( "identifier" );
        }

        DefaultScope defaultScope = null;

        if ( identifier.equals( SINGLETON_SCOPE_IDENTIFIER ) )
        {
            defaultScope = new DefaultScope( new HashMap<String, Object>() );
        }

        return defaultScope;
    }

    /**
     * Gets a locator to use with a given location URI.
     *
     * @param location The location URI to get a locator for.
     * @param classLoader The class loader to use for loading locator implementations.
     *
     * @return The locator to use for locating objects at {@code location} or {@code null} if no such locator is
     * available.
     *
     * @throws NullPointerException if {@code classLoader} or {@code location} is {@code null}.
     * @throws InstantiationException if instantiating a locator fails.
     *
     * @see #getDefaultLocator(java.net.URI)
     */
    public Locator getLocator( final URI location, final ClassLoader classLoader ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        final String scheme = location.getScheme();

        if ( scheme != null )
        {
            final Modules model = this.getModules( classLoader );
            final ClassLoader locatorsLoader = this.getDefaultClassLoader( classLoader );

            synchronized ( this.locators )
            {
                Map<String, Locator> cachedLocators = this.locators.get( locatorsLoader );
                if ( cachedLocators == null )
                {
                    cachedLocators = new HashMap<String, Locator>();
                    this.locators.put( locatorsLoader, cachedLocators );
                }

                Locator locator = cachedLocators.get( scheme );

                if ( locator == null )
                {
                    // Bootstrap locator loading.
                    final Specification locatorSpecification = model.getSpecification( Locator.class );

                    if ( locatorSpecification != null )
                    {
                        final Implementations implementations =
                            model.getImplementations( locatorSpecification.getIdentifier() );

                        if ( implementations != null )
                        {
                            for ( Implementation i : implementations.getImplementation() )
                            {
                                if ( scheme.equals( i.getName() ) )
                                {
                                    final Instance instance = model.getInstance( i.getIdentifier() );

                                    if ( instance != null )
                                    {
                                        locator = (Locator) model.createObject( instance, classLoader );
                                        cachedLocators.put( scheme, locator );

                                        if ( this.isLoggable( Level.CONFIG ) )
                                        {
                                            this.log( classLoader, Level.CONFIG, getLocatorInfoMessage(
                                                Locale.getDefault(), i.getIdentifier(), scheme,
                                                this.getClassLoaderInfo( classLoader, locatorsLoader ) ), null );

                                        }

                                        break;
                                    }
                                    else if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                            Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                                    }
                                }
                            }
                        }
                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                            Locale.getDefault(), Locator.class.getName() ), null );

                    }
                }

                if ( locator == null )
                {
                    locator = this.getDefaultLocator( location );
                    if ( locator != null )
                    {
                        cachedLocators.put( scheme, locator );
                        if ( this.isLoggable( Level.CONFIG ) )
                        {
                            this.log( classLoader, Level.CONFIG, getDefaultLocatorInfoMessage(
                                Locale.getDefault(), scheme,
                                this.getClassLoaderInfo( classLoader, locatorsLoader ) ), null );

                        }
                    }
                }

                return locator;
            }
        }

        return null;
    }

    /**
     * Gets a new default locator implementation instance for a given location URI.
     *
     * @param location The location URI to get a new default locator implementation instance for.
     *
     * @return A new default locator implementation instance for {@code location} or {@code null} if no such instance is
     * available.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     *
     * @see #getLocator(java.net.URI, java.lang.ClassLoader)
     */
    public Locator getDefaultLocator( final URI location )
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        Locator locator = null;
        final DefaultLocator defaultLocator = new DefaultLocator();

        if ( defaultLocator.isLocationSupported( location ) )
        {
            locator = defaultLocator;
        }

        return locator;
    }

    /**
     * Gets the invoker of a given class loader.
     *
     * @param classLoader The class loader to use for loading invoker implementations.
     *
     * @return The invoker of the given class loader.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     * @throws InstantiationException if instantiating a new invoker fails.
     *
     * @see #getDefaultInvoker()
     */
    public Invoker getInvoker( final ClassLoader classLoader ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        final Modules model = this.getModules( classLoader );
        final ClassLoader invokersLoader = this.getDefaultClassLoader( classLoader );

        synchronized ( this.invokers )
        {
            Invoker invoker = this.invokers.get( invokersLoader );

            if ( invoker == null )
            {
                final Specification invokerSpecification = model.getSpecification( Invoker.class );

                if ( invokerSpecification != null )
                {
                    final Implementations implementations =
                        model.getImplementations( invokerSpecification.getIdentifier() );

                    if ( implementations != null && !implementations.getImplementation().isEmpty() )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            if ( invoker == null )
                            {
                                final Instance invokerInstance = model.getInstance( i.getIdentifier() );

                                if ( invokerInstance != null )
                                {
                                    invoker = (Invoker) model.createObject( invokerInstance, classLoader );
                                    this.invokers.put( invokersLoader, invoker );

                                    if ( this.isLoggable( Level.CONFIG ) )
                                    {
                                        this.log( classLoader, Level.CONFIG, getInvokerInfoMessage(
                                            Locale.getDefault(), i.getIdentifier(),
                                            this.getClassLoaderInfo( classLoader, invokersLoader ) ), null );

                                    }
                                }
                                else if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                        Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                                }
                            }
                            else if ( this.isLoggable( Level.CONFIG ) )
                            {
                                this.log( classLoader, Level.CONFIG, getIgnoredInvokerMessage(
                                    Locale.getDefault(), i.getIdentifier() ), null );

                            }
                        }
                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                        Locale.getDefault(), Invoker.class.getName() ), null );

                }

                if ( invoker == null )
                {
                    invoker = this.getDefaultInvoker();
                    this.invokers.put( invokersLoader, invoker );
                    if ( this.isLoggable( Level.CONFIG ) )
                    {
                        this.log( classLoader, Level.CONFIG, getDefaultInvokerInfoMessage(
                            Locale.getDefault(), this.getClassLoaderInfo( classLoader, invokersLoader ) ), null );

                    }
                }
            }

            return invoker;
        }
    }

    /**
     * Gets a new default invoker implementation instance.
     *
     * @return A new default invoker implementation instance.
     *
     * @see #getInvoker(java.lang.ClassLoader)
     *
     * @since 1.1
     */
    public Invoker getDefaultInvoker()
    {
        return new DefaultInvoker();
    }

    /**
     * Gets an invocation for a given object, instance, method and arguments.
     *
     * @param object The object to invoke.
     * @param instance The instance of the object to invoke.
     * @param method The method to invoke on {@code object}.
     * @param arguments The arguments of the invocation or {@code null}.
     *
     * @return An invocation with {@code object}, {@code instance}, {@code method} and {@code arguments}.
     *
     * @throws NullPointerException if {@code object}, {@code instance} or {@code method} is {@code null}.
     * @throws InstantiationException if instantiating a new invocation fails.
     *
     * @deprecated As of JOMC 1.1, please use method {@link #getInvocation(java.lang.ClassLoader, java.lang.Object, org.jomc.model.Instance, java.lang.reflect.Method, java.lang.Object[])}.
     */
    @Deprecated
    public Invocation getInvocation( final Object object, final Instance instance, final Method method,
                                     final Object[] arguments ) throws InstantiationException
    {
        return this.getInvocation( this.getDefaultClassLoader( object.getClass() ), object, instance, method,
                                   arguments );

    }

    /**
     * Gets an invocation for a given class loader, object, instance, method and arguments.
     *
     * @param classLoader The class loader of the invocation.
     * @param object The object to invoke.
     * @param instance The instance of the object to invoke.
     * @param method The method to invoke on {@code object}.
     * @param arguments The arguments of the invocation or {@code null}.
     *
     * @return An invocation with {@code classLoader}, {@code object}, {@code instance}, {@code method} and
     * {@code arguments}.
     *
     * @throws NullPointerException if {@code classLoader} {@code object}, {@code instance} or {@code method} is
     * {@code null}.
     * @throws InstantiationException if instantiating a new invocation fails.
     *
     * @see #getDefaultInvocation()
     *
     * @since 1.1
     */
    public Invocation getInvocation( final ClassLoader classLoader, final Object object, final Instance instance,
                                     final Method method, final Object[] arguments ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( instance == null )
        {
            throw new NullPointerException( "instance" );
        }
        if ( method == null )
        {
            throw new NullPointerException( "method" );
        }

        Invocation invocation = null;
        final Modules model = this.getModules( classLoader );
        final Specification invocationSpecification = model.getSpecification( Invocation.class );

        if ( invocationSpecification != null )
        {
            final Implementations implementations =
                model.getImplementations( invocationSpecification.getIdentifier() );

            if ( implementations != null && !implementations.getImplementation().isEmpty() )
            {
                for ( Implementation i : implementations.getImplementation() )
                {
                    if ( invocation == null )
                    {
                        final Instance invocationInstance = model.getInstance( i.getIdentifier() );

                        if ( invocationInstance != null )
                        {
                            invocation = (Invocation) model.createObject( invocationInstance, classLoader );
                        }
                        else if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( classLoader, Level.WARNING, getMissingInstanceMessage(
                                Locale.getDefault(), i.getIdentifier(), i.getName() ), null );

                        }
                    }
                    else if ( this.isLoggable( Level.CONFIG ) )
                    {
                        this.log( classLoader, Level.CONFIG, getIgnoredInvocationMessage(
                            Locale.getDefault(), i.getIdentifier() ), null );

                    }
                }
            }
        }
        else if ( this.isLoggable( Level.WARNING ) )
        {
            this.log( classLoader, Level.WARNING, getMissingSpecificationMessage(
                Locale.getDefault(), Invocation.class.getName() ), null );

        }

        if ( invocation == null )
        {
            invocation = this.getDefaultInvocation();
        }

        invocation.getContext().put( DefaultInvocation.OBJECT_KEY, object );
        invocation.getContext().put( DefaultInvocation.METHOD_KEY, method );
        invocation.getContext().put( DefaultInvocation.ARGUMENTS_KEY, arguments );
        invocation.getContext().put( DefaultInvocation.INSTANCE_KEY, instance );
        invocation.getContext().put( DefaultInvocation.MODULES_KEY, model );
        invocation.getContext().put( DefaultInvocation.CLASSLOADER_KEY, classLoader );
        return invocation;
    }

    /**
     * Gets a new default invocation implementation instance.
     *
     * @return A new default invocation implementation instance.
     *
     * @see #getInvocation(java.lang.Object, org.jomc.model.Instance, java.lang.reflect.Method, java.lang.Object[])
     *
     * @since 1.1
     */
    public Invocation getDefaultInvocation()
    {
        return new DefaultInvocation();
    }

    /**
     * Initializes the instance.
     * <p>This method is called once on first usage of a new instance.</p>
     *
     * @throws InstantiationException if initialization fails.
     */
    public synchronized void initialize() throws InstantiationException
    {
        if ( !this.initialized )
        {
            try
            {
                final long t0 = System.currentTimeMillis();
                this.initialized = true;

                this.listeners.clear();
                this.modules.clear();
                this.invokers.clear();
                this.locators.clear();
                this.scopes.clear();

                final ClassLoader classLoader = this.getDefaultClassLoader( this.getClass() );
                final List<LogRecord> bootstrapLogRecords = new ArrayList<LogRecord>( 1024 );
                final List<Listener> bootstrapListeners = new ArrayList<Listener>( 1 );
                bootstrapListeners.add( new Listener()
                {

                    public void onLog( final Level level, final String message, final Throwable throwable )
                    {
                        final LogRecord r = new LogRecord( level, message );
                        r.setThrown( throwable );

                        bootstrapLogRecords.add( r );
                    }

                } );

                this.listeners.put( this.getDefaultClassLoader( classLoader ),
                                    Collections.unmodifiableList( bootstrapListeners ) );

                final Modules model = this.getModules( classLoader );
                final Specification objectManager = model.getSpecification( ObjectManager.class );
                if ( objectManager == null )
                {
                    throw new InstantiationException( getMissingSpecificationMessage(
                        Locale.getDefault(), ObjectManager.class.getName() ) );

                }

                final Implementation thisImplementation = model.getImplementation( this.getClass() );
                if ( thisImplementation == null )
                {
                    throw new InstantiationException( getMissingImplementationMessage(
                        Locale.getDefault(), objectManager.getIdentifier(), this.getClass().getName() ) );

                }

                final Instance thisInstance = model.getInstance( this );
                if ( thisInstance == null )
                {
                    throw new InstantiationException( getMissingInstanceMessage(
                        Locale.getDefault(), objectManager.getIdentifier(), thisImplementation.getName() ) );

                }

                if ( objectManager.getScope() != null )
                {
                    final Scope scope = this.getScope( objectManager.getScope(), classLoader );
                    if ( scope == null )
                    {
                        throw new InstantiationException( getMissingScopeMessage(
                            Locale.getDefault(), objectManager.getScope() ) );

                    }

                    scope.putObject( thisInstance.getIdentifier(), this );
                }

                if ( this.isLoggable( Level.FINE ) )
                {
                    this.log( Level.FINE, getImplementationInfoMessage(
                        Locale.getDefault(), Long.valueOf( System.currentTimeMillis() - t0 ) ), null );

                }

                this.listeners.clear();

                for ( LogRecord r : bootstrapLogRecords )
                {
                    this.log( classLoader, r.getLevel(), r.getMessage(), r.getThrown() );
                }
            }
            catch ( final InstantiationException e )
            {
                this.listeners.clear();
                this.modules.clear();
                this.invokers.clear();
                this.locators.clear();
                this.scopes.clear();
                this.initialized = false;

                throw (InstantiationException) new InstantiationException( getMessage( e ) ).initCause( e );
            }
        }
    }

    /**
     * Creates a proxy for a given object.
     *
     * @param instance The instance of {@code object}.
     * @param object The object to create a proxy for.
     * @param classLoader The class loader to create the proxy with.
     *
     * @return A proxy for {@code object}.
     *
     * @throws InstantiationException if creating a proxy fails.
     */
    private Object createProxy( final Instance instance, final Object object, final ClassLoader classLoader )
        throws InstantiationException
    {
        try
        {
            final Set<Class<?>> interfaces = new HashSet<Class<?>>(
                instance.getSpecifications() != null ? instance.getSpecifications().getSpecification().size() : 0 );

            boolean canProxy = instance.getSpecifications() != null;

            if ( canProxy )
            {
                for ( Specification s : instance.getSpecifications().getSpecification() )
                {
                    if ( s.getClazz() != null )
                    {
                        final Class<?> clazz = Class.forName( s.getClazz(), true, classLoader );

                        if ( !clazz.isInterface() )
                        {
                            canProxy = false;
                            break;
                        }

                        interfaces.add( clazz );
                    }
                }
            }

            if ( canProxy && !interfaces.isEmpty() )
            {
                return Proxy.newProxyInstance( classLoader, interfaces.toArray( new Class<?>[ interfaces.size() ] ),
                                               new java.lang.reflect.InvocationHandler()
                {

                    public Object invoke( final Object proxy, final Method method, final Object[] args )
                        throws Throwable
                    {
                        return getInvoker( classLoader ).invoke( getInvocation(
                            classLoader, object, instance, method, args ) );

                    }

                } );

            }

            return object;
        }
        catch ( final ClassNotFoundException e )
        {
            throw (InstantiationException) new InstantiationException( getMessage( e ) ).initCause( e );
        }
    }

    private void logModulesReport( final Modules mods, final ClassLoader classLoader )
    {
        final StringBuilder modulesInfo = new StringBuilder();

        this.log( classLoader, Level.FINEST, getModulesReportMessage( Locale.getDefault() ), null );

        modulesInfo.append( "\tClassLoader:" ).append( classLoader );

        if ( mods.getDocumentation() != null )
        {
            modulesInfo.append( "|Documentation:" ).append( mods.getDocumentation().getText(
                Locale.getDefault().getLanguage() ).getValue() );

        }

        this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );

        for ( Module m : mods.getModule() )
        {
            modulesInfo.setLength( 0 );
            modulesInfo.append( "\tM:" ).append( m.getName() );

            if ( m.getVersion() != null )
            {
                modulesInfo.append( "|Version:" ).append( m.getVersion() );
            }
            if ( m.getVendor() != null )
            {
                modulesInfo.append( "|Vendor:" ).append( m.getVendor() );
            }

            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
            modulesInfo.setLength( 0 );

            if ( m.getSpecifications() != null )
            {
                for ( Specification s : m.getSpecifications().getSpecification() )
                {
                    modulesInfo.append( "\t\t" );
                    this.appendSpecificationInfo( s, modulesInfo );
                    this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                    modulesInfo.setLength( 0 );

                    final Implementations available = mods.getImplementations( s.getIdentifier() );

                    if ( available != null )
                    {
                        for ( Implementation i : available.getImplementation() )
                        {
                            modulesInfo.append( "\t\t\t" );
                            this.appendImplementationInfo( i, modulesInfo ).append( "|Module:" ).
                                append( mods.getModuleOfImplementation( i.getIdentifier() ).getName() );

                            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                            modulesInfo.setLength( 0 );
                        }
                    }
                }
            }

            if ( m.getImplementations() != null )
            {
                for ( Implementation i : m.getImplementations().getImplementation() )
                {
                    modulesInfo.append( "\t\t" );
                    this.appendImplementationInfo( i, modulesInfo );
                    this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                    modulesInfo.setLength( 0 );

                    if ( i.getImplementations() != null )
                    {
                        modulesInfo.append( "\t\t\t" );
                        for ( ImplementationReference r : i.getImplementations().getReference() )
                        {
                            this.appendImplementationInfo(
                                mods.getImplementation( r.getIdentifier() ), modulesInfo ).append( "|Module:" ).
                                append( mods.getModuleOfImplementation( r.getIdentifier() ).getName() );

                            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                            modulesInfo.setLength( 0 );
                        }
                    }
                    if ( i.getSpecifications() != null )
                    {
                        for ( SpecificationReference s : i.getSpecifications().getReference() )
                        {
                            modulesInfo.append( "\t\t\tS:" ).append( s.getIdentifier() );

                            if ( s.getVersion() != null )
                            {
                                modulesInfo.append( "|Version:" ).append( s.getVersion() );
                            }

                            modulesInfo.append( "|Module:" ).append( mods.getModuleOfSpecification(
                                s.getIdentifier() ).getName() );

                            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                            modulesInfo.setLength( 0 );
                        }
                    }

                    if ( i.getDependencies() != null )
                    {
                        for ( Dependency d : i.getDependencies().getDependency() )
                        {
                            modulesInfo.append( "\t\t\tD:" ).append( d.getName() ).append( "|Identifier:" ).
                                append( d.getIdentifier() );

                            if ( d.getImplementationName() != null )
                            {
                                modulesInfo.append( "|Name:" ).append( d.getImplementationName() );
                            }

                            modulesInfo.append( "|Module:" ).append( mods.getModuleOfSpecification(
                                d.getIdentifier() ).getName() );

                            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                            modulesInfo.setLength( 0 );

                            final Implementations available = mods.getImplementations( d.getIdentifier() );

                            if ( available != null )
                            {
                                for ( Implementation di : available.getImplementation() )
                                {
                                    modulesInfo.append( "\t\t\t\t" );
                                    this.appendImplementationInfo( di, modulesInfo ).append( "|Module:" ).
                                        append( mods.getModuleOfImplementation( di.getIdentifier() ).getName() );

                                    this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                                    modulesInfo.setLength( 0 );
                                }
                            }
                        }
                    }

                    if ( i.getMessages() != null )
                    {
                        for ( Message msg : i.getMessages().getMessage() )
                        {
                            modulesInfo.append( "\t\t\tM:" ).append( msg.getName() ).append( "|Text:" ).
                                append( msg.getTemplate().getText( Locale.getDefault().getLanguage() ).getValue() );

                            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                            modulesInfo.setLength( 0 );
                        }
                    }

                    if ( i.getProperties() != null )
                    {
                        for ( Property p : i.getProperties().getProperty() )
                        {
                            modulesInfo.append( "\t\t\tP:" ).append( p.getName() );
                            modulesInfo.append( "|Type:" ).append( p.getType() );
                            modulesInfo.append( "|Value:" ).append( p.getValue() );

                            try
                            {
                                modulesInfo.append( "|JavaValue:" ).append( p.getJavaValue( classLoader ) );
                            }
                            catch ( final PropertyException e )
                            {
                                modulesInfo.append( "|JavaValue:" ).append( e );
                            }

                            this.log( classLoader, Level.FINEST, modulesInfo.toString(), null );
                            modulesInfo.setLength( 0 );
                        }
                    }
                }
            }
        }
    }

    private StringBuilder appendSpecificationInfo( final Specification s, final StringBuilder b )
    {
        b.append( "S:" ).append( s.getIdentifier() );
        if ( s.getVersion() != null )
        {
            b.append( "|Version:" ).append( s.getVersion() );
        }
        if ( s.getVendor() != null )
        {
            b.append( "|Vendor:" ).append( s.getVendor() );
        }

        b.append( "|Multiplicity:" ).append( s.getMultiplicity() ).append( "|Scope:" ).
            append( s.getScope() == null ? "Multiton" : s.getScope() );

        if ( s.getClazz() != null )
        {
            b.append( "|Class:" ).append( s.getClazz() );
        }

        return b;
    }

    private StringBuilder appendImplementationInfo( final Implementation i, final StringBuilder b )
    {
        b.append( "I:" ).append( i.getIdentifier() ).append( "|Name:" ).append( i.getName() ).append( "|Abstract:" ).
            append( i.isAbstract() ).append( "|Final:" ).append( i.isFinal() ).append( "|Stateless:" ).
            append( i.isStateless() );

        if ( i.getVersion() != null )
        {
            b.append( "|Version:" ).append( i.getVersion() );
        }
        if ( i.getVendor() != null )
        {
            b.append( "|Vendor:" ).append( i.getVendor() );
        }
        if ( i.getClazz() != null )
        {
            b.append( "|Class:" ).append( i.getClazz() );
        }
        if ( i.getLocation() != null )
        {
            b.append( "|Location:" ).append( i.getLocation() );
        }

        return b;
    }

    private String getClassLoaderInfo( final ClassLoader current, final ClassLoader parent )
    {
        final StringBuilder b = new StringBuilder();
        appendClassLoaderInfo( b, current );

        if ( parent != null )
        {
            b.append( " => " );
            appendClassLoaderInfo( b, parent );
        }

        return b.toString();
    }

    private String getObjectInfo( final Object object )
    {
        final StringBuilder b = new StringBuilder();
        appendObjectInfo( b, object );
        b.append( " @ " );
        appendClassLoaderInfo( b, this.getDefaultClassLoader( object.getClass() ) );
        return b.toString();
    }

    private static StringBuilder appendClassLoaderInfo( final StringBuilder b, final ClassLoader classLoader )
    {
        return b.append( "(" ).append( Integer.toHexString( System.identityHashCode( classLoader ) ) ).append( ")" ).
            append( classLoader );

    }

    private static StringBuilder appendObjectInfo( final StringBuilder b, final Object object )
    {
        return b.append( "(" ).append( Integer.toHexString( System.identityHashCode( object ) ) ).append( ")" ).
            append( object );

    }

    private static String getMessage( final Throwable t )
    {
        return t != null ? t.getMessage() != null ? t.getMessage() : getMessage( t.getCause() ) : null;
    }

    private static Specification createDefaultSpecification( final Class<?> specification,
                                                             final Multiplicity multiplicity, final String scope )
    {
        final Specification s = new Specification();
        s.setClassDeclaration( true );
        s.setClazz( specification.getName() );
        s.setIdentifier( specification.getName() );
        s.setMultiplicity( multiplicity );
        s.setScope( scope );
        s.setVendor( getDefaultModulesVendor( Locale.getDefault() ) );
        s.setVersion( getDefaultModulesVersion( Locale.getDefault() ) );
        return s;
    }

    private static Implementation createDefaultImplementation( final Class<?> implementation, final String name )
    {
        final Implementation i = new Implementation();
        i.setClassDeclaration( true );
        i.setClazz( implementation.getName() );
        i.setIdentifier( implementation.getName() );
        i.setName( name );
        i.setVendor( getDefaultModulesVendor( Locale.getDefault() ) );
        i.setVersion( getDefaultModulesVersion( Locale.getDefault() ) );
        return i;
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">

    /**
     * Gets the text of the {@code creatingModulesInfo} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Creating modules for ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Erstellt Module f&uuml;r ''{0}''.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code creatingModulesInfo} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getCreatingModulesInfo( final java.util.Locale locale, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "creatingModulesInfo" ), classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultImplementationName} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default JOMC RI</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default JOMC RI</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code defaultImplementationName} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultImplementationName( final java.util.Locale locale )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultImplementationName" ), (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultInvokerInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultInvoker Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 for ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>DefaultInvoker Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 f&uuml;r ''{0}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code defaultInvokerInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultInvokerInfoMessage( final java.util.Locale locale, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultInvokerInfoMessage" ), classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultListenerInfo} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultListener Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 for ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>DefaultListener Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 f&uuml;r ''{0}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code defaultListenerInfo} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultListenerInfo( final java.util.Locale locale, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultListenerInfo" ), classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultLocatorInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultLocator Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scheme ''{0}'' for ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>DefaultLocator Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scheme ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param schemeInfo Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code defaultLocatorInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultLocatorInfoMessage( final java.util.Locale locale, final java.lang.String schemeInfo, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultLocatorInfoMessage" ), schemeInfo, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultLogLevelInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default log level: ''{0}''</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Standard-Protokollierungsstufe: ''{0}''</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param logLevel Format argument.
     * @return The text of the {@code defaultLogLevelInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultLogLevelInfoMessage( final java.util.Locale locale, final java.lang.String logLevel )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultLogLevelInfoMessage" ), logLevel, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModelIdentifierInfo} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default model identifier: ''{0}''</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Standard Modellbezeichner: ''{0}''</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param defaultValue Format argument.
     * @return The text of the {@code defaultModelIdentifierInfo} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModelIdentifierInfo( final java.util.Locale locale, final java.lang.String defaultValue )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModelIdentifierInfo" ), defaultValue, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModelObjectClasspahResolutionEnabledInfo} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default model object classpath resolution enabled: ''{0}''</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Standard Modellobjekt-Klassenpfad-Aufl&ouml;sung aktiviert: ''{0}''</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param defaultValue Format argument.
     * @return The text of the {@code defaultModelObjectClasspahResolutionEnabledInfo} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModelObjectClasspahResolutionEnabledInfo( final java.util.Locale locale, final java.lang.String defaultValue )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModelObjectClasspahResolutionEnabledInfo" ), defaultValue, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModelProcessingEnabledInfo} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default model processing enabled: ''{0}''</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Standard Modellverarbeitung aktiviert: ''{0}''</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param defaultValue Format argument.
     * @return The text of the {@code defaultModelProcessingEnabledInfo} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModelProcessingEnabledInfo( final java.util.Locale locale, final java.lang.String defaultValue )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModelProcessingEnabledInfo" ), defaultValue, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModuleName} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default JOMC RI</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Default JOMC RI</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code defaultModuleName} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModuleName( final java.util.Locale locale )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModuleName" ), (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModulesVendor} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>The JOMC Project</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>The JOMC Project</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code defaultModulesVendor} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModulesVendor( final java.util.Locale locale )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModulesVendor" ), (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModulesVersion} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>1.2-SNAPSHOT</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>1.2-SNAPSHOT</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code defaultModulesVersion} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModulesVersion( final java.util.Locale locale )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModulesVersion" ), (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultModulesWarning} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Failure loading model ''{0}'' for ''{1}''. Creating default modules.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Fehler beim Laden des Modells ''{0}'' f&uuml;r ''{1}''. Erstellt Standard-Module.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param modelInfo Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code defaultModulesWarning} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultModulesWarning( final java.util.Locale locale, final java.lang.String modelInfo, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultModulesWarning" ), modelInfo, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code defaultScopeInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered DefaultScope Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scope ''{0}'' for ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>DefaultScope Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 Scope ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param scopeIdentifier Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code defaultScopeInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDefaultScopeInfoMessage( final java.util.Locale locale, final java.lang.String scopeIdentifier, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "defaultScopeInfoMessage" ), scopeIdentifier, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code dependencyCycleMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>A dependency of implementation ''{0}'' introduces a cycle.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Zyklische Anforderung der Implementierung ''{0}''.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @return The text of the {@code dependencyCycleMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getDependencyCycleMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "dependencyCycleMessage" ), implementationIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code ignoredInvocationMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Invocation implementation ''{0}'' ignored.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Invocation-Implementierung ''{0}'' ignoriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @return The text of the {@code ignoredInvocationMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getIgnoredInvocationMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "ignoredInvocationMessage" ), implementationIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code ignoredInvokerMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Invoker implementation ''{0}'' ignored.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Invoker-Implementierung ''{0}'' ignoriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @return The text of the {@code ignoredInvokerMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getIgnoredInvokerMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "ignoredInvokerMessage" ), implementationIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code illegalArraySpecificationMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Illegal array specification ''{0}''. Mutliplicity ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Ung&uuml;ltige ''Array''-Spezifikation ''{0}''. Kardinalit&auml;t ''{1}''.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param specificationIdentifier Format argument.
     * @param specificationMultiplicity Format argument.
     * @return The text of the {@code illegalArraySpecificationMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getIllegalArraySpecificationMessage( final java.util.Locale locale, final java.lang.String specificationIdentifier, final java.lang.String specificationMultiplicity )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "illegalArraySpecificationMessage" ), specificationIdentifier, specificationMultiplicity, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code illegalObjectSpecificationMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Illegal object specification ''{0}''. Multiplicity ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Ung&uuml;ltige ''Object''-Spezifikation ''{0}''. Kardinalit&auml;t ''{1}''.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param specificationIdentifier Format argument.
     * @param specificationMultiplicity Format argument.
     * @return The text of the {@code illegalObjectSpecificationMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getIllegalObjectSpecificationMessage( final java.util.Locale locale, final java.lang.String specificationIdentifier, final java.lang.String specificationMultiplicity )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "illegalObjectSpecificationMessage" ), specificationIdentifier, specificationMultiplicity, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code implementationInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>DefaultObjectManager Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 initialized in {0,number}ms.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>DefaultObjectManager Version 1.2-SNAPSHOT Build 2011-02-05T04:07:08+0100 in {0,number}ms initialisiert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param initializationMillis Format argument.
     * @return The text of the {@code implementationInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getImplementationInfoMessage( final java.util.Locale locale, final java.lang.Number initializationMillis )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "implementationInfoMessage" ), initializationMillis, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code invokerInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered invoker implementation ''{0}'' for ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Invoker-Implementierung ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code invokerInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getInvokerInfoMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "invokerInfoMessage" ), implementationIdentifier, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code listenerInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered listener implementation ''{0}'' for ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Listener-Implementierung ''{0}'' f&uuml;r ''{1}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code listenerInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getListenerInfoMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "listenerInfoMessage" ), implementationIdentifier, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code locatorInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered ''{1}'' location URI scheme locator implementation ''{0}'' for ''{2}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>''{1}''-Locator-URI-Schema-Implementierung ''{0}'' f&uuml;r ''{2}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param schemeInfo Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code locatorInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getLocatorInfoMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String schemeInfo, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "locatorInfoMessage" ), implementationIdentifier, schemeInfo, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingDependencyMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Dependency ''{1}'' not found for implementation ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>''{1}''-Anforderung der Implementierung ''{0}'' nicht gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param dependencyName Format argument.
     * @return The text of the {@code missingDependencyMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingDependencyMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String dependencyName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingDependencyMessage" ), implementationIdentifier, dependencyName, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingImplementationMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Implementation ''{1}'' not found for specification ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Implementierung ''{1}'' der Spezifikation ''{0}'' nicht gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param specificationIdentifier Format argument.
     * @param implementationName Format argument.
     * @return The text of the {@code missingImplementationMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingImplementationMessage( final java.util.Locale locale, final java.lang.String specificationIdentifier, final java.lang.String implementationName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingImplementationMessage" ), specificationIdentifier, implementationName, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingImplementationsMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>No implementations found for specification ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Keine Implementierungen der Spezifikation ''{0}'' gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param specificationIdentifier Format argument.
     * @return The text of the {@code missingImplementationsMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingImplementationsMessage( final java.util.Locale locale, final java.lang.String specificationIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingImplementationsMessage" ), specificationIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingInstanceMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>No instance found for implementation ''{0}'' - ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Keine Instanz f&uuml;r Implementierung ''{0}'' - ''{1}'' gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param implementationName Format argument.
     * @return The text of the {@code missingInstanceMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingInstanceMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String implementationName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingInstanceMessage" ), implementationIdentifier, implementationName, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingLocatorMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>No locator found for location ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Keinen Locator f&uuml;r Ort ''{0}'' gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param locationInfo Format argument.
     * @return The text of the {@code missingLocatorMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingLocatorMessage( final java.util.Locale locale, final java.lang.String locationInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingLocatorMessage" ), locationInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingMessageMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Message ''{1}'' not found for implementation ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>''{1}''-Meldung der Implementierung ''{0}'' nicht gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param messageName Format argument.
     * @return The text of the {@code missingMessageMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingMessageMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String messageName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingMessageMessage" ), implementationIdentifier, messageName, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingObjectInstanceMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>No instance found for object ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Keine Instanz f&uuml;r Objekt ''{0}'' gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param objectInfo Format argument.
     * @return The text of the {@code missingObjectInstanceMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingObjectInstanceMessage( final java.util.Locale locale, final java.lang.String objectInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingObjectInstanceMessage" ), objectInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingObjectMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>No object found for implementation ''{0}'' - ''{1}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Kein Objekt f&uuml;r Implementierung ''{0}'' - ''{1}'' gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param implementationName Format argument.
     * @return The text of the {@code missingObjectMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingObjectMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String implementationName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingObjectMessage" ), implementationIdentifier, implementationName, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingPropertyMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Property ''{1}'' not found for implementation ''{0}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>''{1}''-Eigenschaft der Implementierung ''{0}'' nicht gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param propertyName Format argument.
     * @return The text of the {@code missingPropertyMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingPropertyMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String propertyName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingPropertyMessage" ), implementationIdentifier, propertyName, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingScopeMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Scope ''{0}'' not found.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>G&uuml;ltigkeitsbereich ''{0}'' nicht gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param scopeIdentifier Format argument.
     * @return The text of the {@code missingScopeMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingScopeMessage( final java.util.Locale locale, final java.lang.String scopeIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingScopeMessage" ), scopeIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingSpecificationClassMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Specification ''{0}'' does not define a class.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Spezifikation ''{0}'' definiert keine Klasse.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param specificationIdentifier Format argument.
     * @return The text of the {@code missingSpecificationClassMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingSpecificationClassMessage( final java.util.Locale locale, final java.lang.String specificationIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingSpecificationClassMessage" ), specificationIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code missingSpecificationMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Specification ''{0}'' not found.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Spezifikation ''{0}'' nicht gefunden.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param specificationIdentifier Format argument.
     * @return The text of the {@code missingSpecificationMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getMissingSpecificationMessage( final java.util.Locale locale, final java.lang.String specificationIdentifier )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "missingSpecificationMessage" ), specificationIdentifier, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code modulesReportMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Modules report:</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Modulbericht:</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code modulesReportMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getModulesReportMessage( final java.util.Locale locale )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "modulesReportMessage" ), (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code scopeInfoMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Registered ''{1}'' scope implementation ''{0}'' for ''{2}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>''{1}''-G&uuml;ltigkeitsbereich-Implementierung ''{0}'' f&uuml;r ''{2}'' registriert.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param scopeIdentifier Format argument.
     * @param classLoaderInfo Format argument.
     * @return The text of the {@code scopeInfoMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getScopeInfoMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String scopeIdentifier, final java.lang.String classLoaderInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "scopeInfoMessage" ), implementationIdentifier, scopeIdentifier, classLoaderInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Gets the text of the {@code unexpectedDependencyObjectsMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Unexpected number of objects for dependency ''{1}'' of implementation ''{0}''. Expected ''{2,number}'' - found ''{3,number}''.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Unerwartete Anzahl Objekte f&uuml;r ''{1}''-Anforderung der Implementierung ''{0}''. Erwartet ''{2,number}'' - gefunden ''{3,number}''.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param implementationIdentifier Format argument.
     * @param dependencyName Format argument.
     * @param expectedNumber Format argument.
     * @param computedNumber Format argument.
     * @return The text of the {@code unexpectedDependencyObjectsMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private static String getUnexpectedDependencyObjectsMessage( final java.util.Locale locale, final java.lang.String implementationIdentifier, final java.lang.String dependencyName, final java.lang.Number expectedNumber, final java.lang.Number computedNumber )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager", locale ).getString( "unexpectedDependencyObjectsMessage" ), implementationIdentifier, dependencyName, expectedNumber, computedNumber, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }
    // </editor-fold>
    // SECTION-END
}
