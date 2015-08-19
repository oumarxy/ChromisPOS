//    Chromis POS  - The New Face of Open Source POS
//    Copyright (c) 2015 
//    http://www.chromis.co.uk
//
//    This file is part of Chromis POS
//
//     Chromis POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Chromis POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>.

package uk.chromis.data.loader;

import uk.chromis.basic.BasicException;

/**
 *
 *   
 */
public class SerializerReadClass implements SerializerRead {

    private final Class m_clazz;
    
    /** Creates a new instance of DefaultSerializerRead
     * @param clazz */
    public SerializerReadClass(Class clazz) {
        m_clazz = clazz;
    }
    
    /**
     *
     * @param dr
     * @return
     * @throws BasicException
     */
    @Override
    public Object readValues(DataRead dr) throws BasicException {
        try {
            SerializableRead sr = (SerializableRead) m_clazz.newInstance();
            sr.readValues(dr);
            return sr;
// JG 16 May 12 use multicatch
        } catch (java.lang.InstantiationException | IllegalAccessException | ClassCastException eIns) {
            return null;
        }
    }
}
