/*
 * This file is part of "Urkaz Mod Tools"".
 * Copyright (C) 2023 Urkaz
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.urkaz.moontools;

import com.urkaz.moontools.common.UrkazMoonToolsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "urkazmoontools";
	public static final String MOD_NAME = "Urkaz Moon Tools";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static UrkazMoonToolsConfig CONFIG = new UrkazMoonToolsConfig();

	//Enhanced Celestials
	public static final String MOD_ENHANCED_CELESTIALS_ID = "enhancedcelestials";
}