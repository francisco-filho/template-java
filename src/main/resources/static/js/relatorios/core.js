function Header(columns, groups){
    this.columns = columns;
    this.groups = groups;
}

Header.prototype.createRow = function(row){
    var columns = this.columns,
        groups = this.groups;

    var len = columns.length;
    var res = [];

    for (var i = 0; i < len; i++){
        var col = columns[i];
        var column = {};
        var rows = _.max(columns, 'pos').pos;

        if (col.pos == row ) {
            column.name = col.name;
            column.rowspan =  (_.max(columns, 'pos').pos - col.pos) + 1 ;
        } else {
            var g = _.groupBy(groups, "name")[col.name];
            if (g){
                if (g[row - 1]){
                    column.name= (g[row -1].group_name);
                    column.rowspan = 1;
                }
            }
        }
        res.push(column);
    }
    return res;
}

Header.prototype.normalizeRow = function(row){
    var columns = this.columns,
        groups = this.groups;

    var last = { name: undefined};
    var res = [];
    var count = _.countBy(row, 'name');
    for (var i = 0; i < row.length; i++){
        if (row[i].name != last.name){
            row[i].colspan = count[row[i].name];
            res.push(row[i]);
        } else {
            continue;
        }
        last = row[i];
    }
    if (count > 1){
        row[i].colspan = count[row[i].name];;
        res.push(row[i]);
    }

    return res;
}

Header.prototype.render = function(){
    var columns = this.columns,
        groups = this.groups;

    var len = _.max(columns, 'pos').pos,
        res = [];

    for (var i = 0; i < len; i++){
        var rows = this.normalizeRow(this.createRow(i+1));

        res.push('<tr>');

        for (var y = 0; y < rows.length; y++){
            if (rows[y].name){
                res.push('<th');
                if (rows[y].colspan > 1){
                    res.push(' colspan="'+rows[y].colspan+'"')
                }
                if (rows[y].rowspan > 1){
                    res.push(' rowspan="'+rows[y].rowspan+'"')
                }
                res.push('>'+rows[y].name+'</th>');
            }
        }

        res.push('</tr>');
    }

    return res.join("");
}

function renderRows(rows){
    var res  = [],
        currentRow;
    //rows
    for(var i = 0; i < rows.length; i++){
        res.push('<tr>');
        //columns
        currentRow = rows[i];
        for(col in rows[i]){
            if (rows[i].hasOwnProperty(col)){
                res.push('<td>' + currentRow[col] + '</td>');
            }
        }
        res.push('</tr>');
    }
    return res.join("");
}