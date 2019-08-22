/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function SetAllCheckBoxes (FormName, FieldName, CheckValue)
{
    if (!document.forms[FormName])
    {
        return;
    }

    var objCheckBoxes = document.forms[FormName].elements[FieldName];

    if (!objCheckBoxes)
    {
        return;
    }

    var countCheckBoxes = objCheckBoxes.length;

    if (!countCheckBoxes)
    {
        objCheckBoxes.checked = CheckValue;
    }
    else
    {// set the check value for all check boxes
        for (var i = 0; i < countCheckBoxes; i++)
        {
            objCheckBoxes[i].checked = CheckValue;
        }
    }
}
