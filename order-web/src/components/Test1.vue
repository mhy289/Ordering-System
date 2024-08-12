<template>
    <div id="app">
      <el-row class="m20">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="当前状态">
            <el-select v-model="formInline.staus" placeholder="状态">
              <el-option label="启用" value="1"></el-option>
              <el-option label="停用" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="企业名称">
            <el-input v-model="formInline.companyName" placeholder="企业名称"></el-input>
          </el-form-item>
          <el-form-item label="品牌名称">
            <el-input v-model="formInline.brandName" placeholder="品牌名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onQuery">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
  
      <el-row class="m20">
        <el-button type="danger" @click="handleBatchDelete" plain>批量删除</el-button>
        <el-button type="primary" @click="dialogFormVisible=true" plain>新增</el-button>
      </el-row>
  
      <el-dialog title="新增品牌" :visible.sync="dialogFormVisible">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="品牌名称">
            <el-input v-model="form.brandName"></el-input>
          </el-form-item>
          <el-form-item label="企业名称">
            <el-input v-model="form.companyName"></el-input>
          </el-form-item>
          <el-form-item label="排序">
            <el-input v-model.number="form.ordered"></el-input>
          </el-form-item>
          <el-form-item label="公司描述">
            <el-input type="textarea" v-model="form.description"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="form.status" active-value="1" inactive-value="0"></el-switch>
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" @click="insertRec">提交</el-button>
            <el-button @click="dialogFormVisible = false">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
              </div> -->
      </el-dialog>
  
      <el-dialog title="编辑品牌" :visible.sync="dialogFormVisible2">
        <el-form ref="form2" :model="form2" label-width="80px">
          <el-form-item label="品牌名称">
            <el-input v-model="form2.brandName"></el-input>
          </el-form-item>
          <el-form-item label="企业名称">
            <el-input v-model="form2.companyName"></el-input>
          </el-form-item>
          <el-form-item label="排序">
            <el-input v-model.number="form2.ordered"></el-input>
          </el-form-item>
          <el-form-item label="公司描述">
            <el-input type="textarea" v-model="form2.description"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="form2.status" active-value="1" inactive-value="0"></el-switch>
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" @click="updRec">提交</el-button>
            <el-button @click="dialogFormVisible2 = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
  
      <el-row class="m20">
        <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column prop="id" label="ID" width="180">
          </el-table-column>
          <el-table-column prop="brandName" label="品牌名" width="180">
          </el-table-column>
          <el-table-column prop="companyName" label="公司名" width="180">
          </el-table-column>
          <el-table-column prop="ordered" label="排序" width="180">
          </el-table-column>
          <el-table-column prop="description" label="品牌介绍" width="180">
          </el-table-column>
          <el-table-column prop="status" label="状态" width="123">
          </el-table-column>
          <el-table-column label="操作"  width="180">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row class="m20">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="arrPage" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </el-row>
    </div>
  </template>
  
  <script>
    export default {
      data() {
        return {
          tableData: [],
          multipleSelection: [],
          form: {},
          dialogFormVisible: false,
          dialogFormVisible2: false,
          form2: {},
          /* formLabelWidth: '120px',
          currentPage: 1,
          pageSize: 10, */
          formLabelWidth: '120px',
          currentPage: 1,
          pageSize: 10,
          arrPage: [10, 20, 30, 50],
          total: 50,
          formInline: {}
        }
      },
      mounted() {
        //this.queryAll();
        this.qurreyPage()
      },
      methods: {
        handleSizeChange(val) {
          console.log(`每页 ${val} 条`);
          this.pageSize = val;
          this.currentPage = 1
          this.qurreyPage()
        },
        handleCurrentChange(val) {
          console.log(`当前页: ${val}`);
          this.currentPage = val;
          this.qurreyPage()
        },
        qurreyPage: async function () {
          //console.log(this)
          let res = await this.$http.get("brand/selectPage", {
            params: {
              current: this.currentPage,
              size: this.pageSize
            }
          })
          console.log(res)
          this.tableData = res.brandList
          this.total = res.total
        },
        queryAll: async function () {
          let res = await this.$http.get('/brand/selectAll')
          console.log(res);
  
          this.tableData = res
        },
        tableRowClassName({
          row,
          rowIndex
        }) {
          if (rowIndex % 2 == 1) {
            return 'warning-row';
          } else if (rowIndex % 2 == 0) {
            return 'success-row';
          }
          return '';
        },
        handleEdit: async function (index, row) {
          //console.log("000");
          console.log(row.id);
          console.log("")
          console.log(this)
          //console.log(index, row); 
          this.dialogFormVisible2 = true
          this.form2 = {
            ...row
          }
          //let res = await axios.get("select",row.id)
  
          //console.log(res.brandList);
        },
        handleDelete(index, row) {
          //console.log(index, row);
          //console.log(row.id, row);
          //console.log(row.brandList)
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            //let idList = this.multipleSelection.map(brand => brand.id)
            console.log(row.id);
            //console.log(row.brandList)
            //let id = row.id
            let res = await this.$http.get("delete?id=" + row.id);
            console.log(res.status)
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: res.message
              });
              console.log(res.message);
              //alert('成功')
              //this.queryAll()
              this.qurreyPage()
            } else {
              console.log('删除失败')
              //alert('删除失败')
              //this.queryAll()
              this.$message({
                type: 'error',
                message: res.message
              })
            }
  
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
          console.log(val);
        },
        handleBatchDelete: async function () {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            let idList = this.multipleSelection.map(brand => brand.id)
            console.log(idList);
            let res = await this.$http.post("deleteMore", idList);
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: res.message
              });
              console.log(res.message);
              //alert('成功')
              //this.queryAll()
              this.qurreyPage()
            } else {
              console.log('删除失败')
              //alert('删除失败')
              //this.queryAll()
              this.$message({
                type: 'error',
                message: res.message
              })
            }
  
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
  
  
        },
        onQuery: async function () {
          console.log(this.formInline)
          let params = "current=" + this.currentPage + "&" + "size=" + this.pageSize
          console.log(params)
          let res = await this.$http.post("/brand/selectCond?" + params, this.formInline)
          console.log(res)
          this.tableData = res.brandList
          this.total = res.total
          this.formInline = {}
        },
        insertRec: async function () {
          console.log(this.form)
          let res = await this.$http.post("insert", this.form)
          if (res.status === 200) {
            //alert('成功')
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.dialogFormVisible = false
            //this.queryAll()
            this.qurreyPage()
          } else {
            console.log('添加失败')
            //alert('添加失败')
            this.$message({
              message: res.message,
              type: 'error'
            })
          }
        },
        updRec: async function () {
          console.log(this.form2)
          let res = await this.$http.post("update", this.form2)
          if (res.status === 200) {
            //alert('成功')
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.dialogFormVisible2 = false
            //this.queryAll()
            this.qurreyPage()
          } else {
            console.log('修改失败')
            //alert('修改失败')
            this.$message({
              message: res.message,
              type: 'error'
            })
          }
        }
      }
    }
  
  </script>
  
  <style scoped>
    .el-table .warning-row {
      background: oldlace;
    }
  
    .el-table .success-row {
      background: #f0f9eb;
    }
  
    .m20 {
      margin-bottom: 20px;
    }
  
    .el-dialog {
      width: 5000px;
    }
  
  </style>
  