<template>
  <div>
    <!-- 搜索和功能按钮区域 -->
    <div style="padding: 5px 0">
      <el-input
        v-model="searchText"
        @keyup.enter.native="load"
        placeholder="搜索食品"
        style="width: 200px"
      >
        <i
          slot="prefix"
          class="el-input__icon el-icon-search"
        ></i></el-input>
      <el-button
        @click="load"
        round
        type="primary"
        style="margin: 5px"
      >搜索</el-button>
      <el-button
        @click="reset"
        round
        type="info"
        style="margin: 5px"
      >重置</el-button>
      <el-button
        @click="showAddDialog"
        round
        type="success"
        style="margin: 5px"
      >新增食品</el-button>
    </div>

    <!-- 食品数据表格 -->
    <el-table
      :data="tableData"
      stripe
      border
      style="width: 100%"
    >
      <el-table-column
        prop="id"
        label="食品ID"
        width="80px"
      ></el-table-column>

      <el-table-column
        prop="dishname"
        label="食品名称"
      ></el-table-column>

      <el-table-column
        label="食品图片"
        width="120px"
      >
        <template slot-scope="scope">
          <img
            :src="baseApi + scope.row.imgs"
            style="width: 90px; height: 80px;"
          >
        </template>
      </el-table-column>

      <el-table-column
        prop="description"
        label="食品描述"
      ></el-table-column>

      <el-table-column
        prop="price"
        label="价格"
      ></el-table-column>

      <el-table-column
        prop="categoryId"
        label="分类"
      ></el-table-column>

      <el-table-column
        prop="recommend"
        label="推荐"
        width="150px"
      >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.recommend"
            @change="handleRecommend(scope.row)"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="200px"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            circle
            @click="edit(scope.row)"
          ></el-button>
          <el-popconfirm
            @confirm="del(scope.row.id)"
            title="确定删除该食品吗？"
          >
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              slot="reference"
              style="margin-left: 10px;"
            ></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页控件 -->
    <div style="margin-top: 10px;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-size="pageSize"
        :page-sizes="[5, 10, 20, 50]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>

    <!-- 新增食品弹窗 -->
    <el-dialog
      title="新增食品"
      :visible.sync="dialogFormVisible"
      width="40%"
      :close-on-click-modal="false"
    >
      <el-form :model="entity">
        <el-form-item
          label="食品名称"
          label-width="120px"
        >
          <el-input
            v-model="entity.dishname"
            autocomplete="off"
            style="width: 100%;"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="食品描述"
          label-width="120px"
        >
          <el-input
            v-model="entity.description"
            autocomplete="off"
            type="textarea"
            style="width: 100%;"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="价格"
          label-width="120px"
        >
          <el-input
            v-model="entity.price"
            type="number"
            autocomplete="off"
            style="width: 100%;"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="分类ID"
          label-width="120px"
        >
          <el-input
            v-model="entity.categoryId"
            autocomplete="off"
            style="width: 100%;"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="食品图片"
          label-width="120px"
        >
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="baseApi + '/file/upload'"
            :file-list="fileList"
            :on-change="handleChange"
            :limit="1"
            :on-success="handleImgSuccess"
            :auto-upload="false"
          >
            <el-button
              slot="trigger"
              size="small"
              type="primary"
            >选取文件</el-button>
            <div
              slot="tip"
              class="el-upload__tip"
            >
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
        </el-form-item>
      </el-form>

      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="save"
        >确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios';

const url = "/api/good/";

export default {
  name: "Foods",
  data () {
    return {
      //baseApi: this.$store.state.baseApi,
      fileList: [],
      searchText: '',
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      entity: {},
      total: 0,
      dialogFormVisible: false,
    };
  },
  created () {
    this.load();
  },
  methods: {
    handleSizeChange (pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange (pageNum) {
      this.pageNum = pageNum;
      this.load();
    },
    handleRecommend (food) {
      axios.get(url + "recommend", {
        params: {
          id: food.id,
          isRecommend: food.recommend,
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.$message.success("修改成功");
        } else {
          this.$message.error(res.data.msg);
        }
      });
    },
    load () {
      axios.get(url + "fullPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          searchText: this.searchText
        }
      }).then(res => {
        this.tableData = res.data.records;
        this.total = res.data.total;
      });
    },
    reset () {
      //this.searchText = '';
      this.load();
    },
    showAddDialog () {
      this.entity = {};
      this.dialogFormVisible = true;
    },
    edit (food) {
      this.entity = JSON.parse(JSON.stringify(food));
      this.dialogFormVisible = true;
    },
    handleImgSuccess (res) {
      this.entity.imgs = res.data;
      this.save();
    },
    save () {
      if (this.fileList.length !== 0) {
        this.$refs.upload.submit();
      } else {
        axios.post(url, this.entity).then(res => {
          if (res.data.code === '200') {
            this.$message({
              type: "success",
              message: "操作成功"
            });
            this.dialogFormVisible = false;
            this.load();
          } else {
            this.$message({
              type: "error",
              message: res.data.msg
            });
          }
        });
      }
    },
    del (id) {
      axios.delete(url + id).then(res => {
        if (res.data.code === '200') {
          this.$message({
            type: "success",
            message: "删除成功"
          });
          this.load();
        } else {
          this.$message({
            type: "error",
            message: res.data.msg
          });
        }
      });
    },
    handleChange (file, fileList) {
      this.fileList = fileList.slice(-1);
    }
  }
};
</script>
