/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2009 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 */
package com.serotonin.bacnet4j.service.acknowledgement;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class VtDataAck extends AcknowledgementService {
    public static final byte TYPE_ID = 23;
    
    private final Boolean allNewDataAccepted;
    private final UnsignedInteger acceptedOctetCount;
    
    public VtDataAck(Boolean allNewDataAccepted, UnsignedInteger acceptedOctetCount) {
        this.allNewDataAccepted = allNewDataAccepted;
        this.acceptedOctetCount = acceptedOctetCount;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, allNewDataAccepted, 0);
        writeOptional(queue, acceptedOctetCount, 1);
    }
    
    VtDataAck(ByteQueue queue) throws BACnetException {
        allNewDataAccepted = read(queue, Boolean.class, 0);
        acceptedOctetCount = readOptional(queue, UnsignedInteger.class, 1);
    }
    
    public Boolean getAllNewDataAccepted() {
        return allNewDataAccepted;
    }

    public UnsignedInteger getAcceptedOctetCount() {
        return acceptedOctetCount;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((acceptedOctetCount == null) ? 0 : acceptedOctetCount.hashCode());
        result = PRIME * result + ((allNewDataAccepted == null) ? 0 : allNewDataAccepted.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VtDataAck other = (VtDataAck) obj;
        if (acceptedOctetCount == null) {
            if (other.acceptedOctetCount != null)
                return false;
        }
        else if (!acceptedOctetCount.equals(other.acceptedOctetCount))
            return false;
        if (allNewDataAccepted == null) {
            if (other.allNewDataAccepted != null)
                return false;
        }
        else if (!allNewDataAccepted.equals(other.allNewDataAccepted))
            return false;
        return true;
    }
}
