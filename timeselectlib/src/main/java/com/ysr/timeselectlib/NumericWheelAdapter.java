/*
 * Copyright 2010 Yuri Kanivets
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ysr.timeselectlib;

/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter implements WheelAdapter {

	/** The default min value */
	public static final int DEFAULT_MAX_VALUE = 9;

	/** The default max value */
	private static final int DEFAULT_MIN_VALUE = 0;

	// Values
	private int minValue;

	private int maxValue;
	private int min;
	// format
	private String format;

	/**
	 * Default constructor
	 */
	public NumericWheelAdapter() {
		this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}

	/**
	 * Constructor
	 * 
	 * @param minValue
	 *            the wheel min value
	 * @param maxValue
	 *            the wheel max value
	 */
	public NumericWheelAdapter(int minValue, int maxValue) {
		this(minValue, maxValue, null,0);
	}

	/**
	 * Constructor
	 * 
	 * @param minValue
	 *            the wheel min value
	 * @param maxValue
	 *            the wheel max value
	 * @param format
	 *            the format string
	 */
	public NumericWheelAdapter(int minValue, int maxValue, String format,int min) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.format = format;
		this.min=min;
	}

	@Override
	public String getItem(int index) {
		if (index >= 0) {
			if (format == null) {
				int value = minValue + index % getItemsCount();
				return Integer.toString(value);
			} else if (format.equals("minute")) {
				int mvalue = minValue + index % getItemsCount() * min;
				return Integer.toString(mvalue);
			}
			// return format != null ? String.format(format, value) : Integer
			// .toString(value);
		}
		// if(index>=0 && index>=getItemsCount())
		// {
		// int value = minValue + index%getItemsCount();
		// return format != null ? String.format(format, value) :
		// Integer.toString(value);
		// }
		return null;
	}

	@Override
	public int getItemsCount() {
		if (format == null) {
			return maxValue - minValue + 1;
		} else if (format.equals("minute")) {
			return (maxValue - minValue) / min + 1;
		}
		return 11;
	}

	@Override
	public int getMaximumLength() {
		if (format == null) {
			int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
			int maxLen = Integer.toString(max).length();
			if (minValue < 0) {
				maxLen++;
			}
			return maxLen;
		} else if (format.equals("minute")) {
			return (maxValue - minValue) / min + 1;
		}

		return 11;
	}
}