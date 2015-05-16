/* Copyright 2008 Marcel Overdijk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class HexCodec {

    static HEX_DIGITS = "0123456789abcdef"

    /**
     * Returns the hexadecimal String representation of the target
     *
     * @param theTarget the array or list of numbers to encode as hexadecimal
     */
    static encode = { theTarget ->
        if (theTarget == null) {
            return null
        }
        else {
            def result = new StringBuffer()
            theTarget.each() {
                result << HexCodec.HEX_DIGITS[(it & 0xF0) >> 4]
                result << HexCodec.HEX_DIGITS[(it & 0x0F)]
            }
            return result.toString()
        }
    }
}