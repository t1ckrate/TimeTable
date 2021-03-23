/*
 * MIT License
 *
 * Copyright (c) 2021 Thomas PERROT / t1ckrate (https://github.com/t1ckrate).
 * Mailing at mail@t1ckrate.fr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders X be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software.
 * Except as contained in this notice, the name of copyright holder (Thomas PERROT) shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization from the copyright holder (Thomas PERROT).
 *
 */

package fr.t1ckrate.services.embed.embedtypes;

import fr.t1ckrate.services.embed.IEmbed;

import java.awt.*;

public class TitleEmbed implements IEmbed {

    @Override
    public String getTitle() {
        return "Ajouter un évènement à l'agenda";
    }

    @Override
    public String getDescription() {
        return "\uD83D\uDCCC **|** Veuillez écrire le titre de cet évènement";
    }

    @Override
    public String getFooter() {
        return "TimeTable";
    }

    @Override
    public String getFooterUrl() {
        return "https://t1ckrate.fr/img/timetable.png";
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
