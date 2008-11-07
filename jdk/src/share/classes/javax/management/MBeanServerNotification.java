/*
 * Copyright 1999-2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package javax.management;


/**
 * Represents a notification emitted by the MBean Server through the MBeanServerDelegate MBean.
 * The MBean Server emits the following types of notifications: MBean registration, MBean
 * unregistration.
 * <P>
 * To receive MBeanServerNotifications, you need to register a listener with
 * the {@link MBeanServerDelegate MBeanServerDelegate} MBean
 * that represents the MBeanServer. The ObjectName of the MBeanServerDelegate is
 * {@link MBeanServerDelegate#DELEGATE_NAME}, which is
 * <CODE>JMImplementation:type=MBeanServerDelegate</CODE>.
 *
 * <p>The following code prints a message every time an MBean is registered
 * or unregistered in the MBean Server {@code mbeanServer}:</p>
 *
 * <pre>
 * private static final NotificationListener printListener = new NotificationListener() {
 *     public void handleNotification(Notification n, Object handback) {
 *         if (!(n instanceof MBeanServerNotification)) {
 *             System.out.println("Ignored notification of class " + n.getClass().getName());
 *             return;
 *         }
 *         MBeanServerNotification mbsn = (MBeanServerNotification) n;
 *         String what;
 *         if (n.getType().equals(MBeanServerNotification.REGISTRATION_NOTIFICATION))
 *             what = "MBean registered";
 *         else if (n.getType().equals(MBeanServerNotification.UNREGISTRATION_NOTIFICATION))
 *             what = "MBean unregistered";
 *         else
 *             what = "Unknown type " + n.getType();
 *         System.out.println("Received MBean Server notification: " + what + ": " +
 *                 mbsn.getMBeanName());
 * };
 *
 * ...
 *     mbeanServer.addNotificationListener(
 *             MBeanServerDelegate.DELEGATE_NAME, printListener, null, null);
 * </pre>
 *
 * @since 1.5
 */
public class MBeanServerNotification extends Notification {


    /* Serial version */
    private static final long serialVersionUID = 2876477500475969677L;
    /**
     * Notification type denoting that an MBean has been registered.
     * Value is "JMX.mbean.registered".
     */
    public static final String REGISTRATION_NOTIFICATION =
            "JMX.mbean.registered";
    /**
     * Notification type denoting that an MBean has been unregistered.
     * Value is "JMX.mbean.unregistered".
     */
    public static final String UNREGISTRATION_NOTIFICATION =
            "JMX.mbean.unregistered";
    /**
     * @serial The object names of the MBeans concerned by this notification
     */
    private final ObjectName objectName;

    /**
     * Creates an MBeanServerNotification object specifying object names of
     * the MBeans that caused the notification and the specified notification
     * type.
     *
     * @param type A string denoting the type of the
     * notification. Set it to one these values: {@link
     * #REGISTRATION_NOTIFICATION}, {@link
     * #UNREGISTRATION_NOTIFICATION}.
     * @param source The MBeanServerNotification object responsible
     * for forwarding MBean server notification.
     * @param sequenceNumber A sequence number that can be used to order
     * received notifications.
     * @param objectName The object name of the MBean that caused the
     * notification.
     *
     */
    public MBeanServerNotification(String type, Object source,
            long sequenceNumber, ObjectName objectName) {
        super(type, source, sequenceNumber);
        this.objectName = objectName;
    }

    /**
     * Returns the  object name of the MBean that caused the notification.
     *
     * @return the object name of the MBean that caused the notification.
     */
    public ObjectName getMBeanName() {
        return objectName;
    }

    @Override
    public String toString() {
        return super.toString() + "[mbeanName=" + objectName + "]";

    }

 }
