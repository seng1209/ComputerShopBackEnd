import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";

const UpdateImport = () => {
  const { uuid } = useParams();

  const [disable, setDisable] = useState(false);

  const [suppliers, setSuppliers] = useState([]);

  const [staffs, setStaffs] = useState([]);

  const [products, setProducts] = useState([]);

  const [importRequest, setImportRequest] = useState({
    supplierUuid: "",
    staffUuid: "",
  });

  const { supplierUuid, staffUuid } = importRequest;

  const [importsProductsRequest, setImportsProductsRequest] = useState({
    importUuid: "",
    productUuid: "",
    importQuantity: "",
    unitPrice: "",
  });

  const { productUuid, importQuantity, unitPrice } = importsProductsRequest;

  const [importsProductsResponse, setImportsProductsResponse] = useState([]);

  const [importUuid, setImportUuid] = useState();

  const [importsProductsUuid, setImportsProductsUuid] = useState();

  useEffect(() => {
    loadSuppliers();
    loadStaffs();
    loadProducts();
    findByUuid();
  }, []);

  const findByUuid = async () => {
    try {
      const importResult = await axios.get(
        `http://localhost:8080/api/v1/imports/${uuid}`
      );
      setImportRequest({
        ...importRequest,
        supplierUuid: importResult.data.supplier.uuid,
        staffUuid: importResult.data.staff.uuid,
      });
      const importsProductsResult = await axios.get(
        `http://localhost:8080/api/v1/imports_products/import/${uuid}`
      );
      setImportsProductsResponse(importsProductsResult.data);
    } catch (err) {
      console.log(err);
    }
  };

  const loadSuppliers = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/suppliers`);
      setSuppliers(result.data);
    } catch (err) {
      console.error(err);
    }
  };

  const loadStaffs = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/staffs`);
      setStaffs(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const loadProducts = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/products`);
      setProducts(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const loadImportsProducts = async () => {
    console.log(importUuid);
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/imports_products/import/${importUuid}`
      );
      setImportsProductsResponse(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  // Master
  const onMasterChange = (e) => {
    setImportRequest({ ...importRequest, [e.target.name]: e.target.value });
  };

  const onSubmitMasterForm = async () => {
    console.log(importRequest);
    try {
      const result = await axios.patch(
        `http://localhost:8080/api/v1/imports/${uuid}`,
        importRequest
      );
      setImportUuid(result.data.uuid);
      // console.log(result.data.uuid);
    } catch (err) {
      console.log(err);
    }
    setDisable(true);
  };

  // Detail

  const onDetailChange = async (e) => {
    setImportsProductsRequest({
      ...importsProductsRequest,
      [e.target.name]: e.target.value,
    });
  };

  const onSubmitDetailForm = async () => {
    const importsProductsRequestDto = {
      ...importsProductsRequest,
      //   importsUuid: importUuid,
    };
    console.log(importsProductsRequestDto);
    try {
      const result = await axios.patch(
        `http://localhost:8080/api/v1/imports_products/${importsProductsUuid}`,
        importsProductsRequestDto
      );
      await axios.put(
        `http://localhost:8080/api/v1/imports_products/imports/${importUuid}`
      );
    } catch (err) {
      console.log(err);
    }
    loadImportsProducts();
    alert("add successully");
  };

  const onCheck = async (uuid) => {
    setImportsProductsUuid(uuid);
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/imports_products/${uuid}`
      );
      setImportsProductsRequest({
        ...importsProductsRequest,
        importUuid: result.data.imports.uuid,
        productUuid: result.data.product.uuid,
        importQuantity: result.data.importQuantity,
        unitPrice: result.data.unitPrice,
      });
      setImportUuid(result.data.imports.uuid);
      console.log(result.data);
      console.log(importsProductsRequest);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div>
      <Container>
        <Row>
          <Col>
            <Form>
              <Row>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Supplier Name</Form.Label>
                    <Form.Select
                      name="supplierUuid"
                      value={supplierUuid}
                      onChange={(e) => onMasterChange(e)}
                      aria-label="Default select example"
                    >
                      <option disabled selected>
                        Open this select menu
                      </option>
                      {suppliers.map((supplier, index) => {
                        return (
                          <option key={index} value={supplier.uuid}>
                            {supplier.name}
                          </option>
                        );
                      })}
                    </Form.Select>
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Staff Name</Form.Label>
                    <Form.Select
                      name="staffUuid"
                      value={staffUuid}
                      onChange={(e) => onMasterChange(e)}
                      aria-label="Default select example"
                    >
                      <option disabled selected>
                        Open this select menu
                      </option>
                      {staffs.map((staff, index) => {
                        return (
                          <option key={index} value={staff.uuid}>
                            {staff.name}
                          </option>
                        );
                      })}
                    </Form.Select>
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col md={4}>
                  <Button
                    disabled={disable}
                    variant="primary"
                    onClick={onSubmitMasterForm}
                  >
                    Update Import
                  </Button>
                </Col>
              </Row>
            </Form>
          </Col>
          <Col>
            <Form>
              <Row>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Product Name</Form.Label>
                    <Form.Select
                      disabled
                      name="productUuid"
                      value={productUuid}
                      onChange={(e) => onDetailChange(e)}
                      aria-label="Default select example"
                    >
                      <option disabled selected>
                        Open this select menu
                      </option>
                      {products.map((product, index) => {
                        return (
                          <option key={index} value={product.uuid}>
                            {product.name}
                          </option>
                        );
                      })}
                    </Form.Select>
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Import Quantity</Form.Label>
                    <Form.Control
                      type="number"
                      name="importQuantity"
                      value={importQuantity}
                      onChange={(e) => onDetailChange(e)}
                      placeholder="import quantity"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col md={6}>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Unit Price</Form.Label>
                    <Form.Control
                      type="number"
                      name="unitPrice"
                      value={unitPrice}
                      onChange={(e) => onDetailChange(e)}
                      placeholder="unit price"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Button variant="primary" onClick={onSubmitDetailForm}>
                    Add New
                  </Button>
                </Col>
                <Col>
                  <Button variant="danger" href="/import">
                    Exit
                  </Button>
                </Col>
              </Row>
            </Form>
          </Col>
        </Row>
      </Container>
      <Container>
        <Row>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>ID</th>
                <th>Import UUID</th>
                <th>Product Name</th>
                <th>Import Quantity</th>
                <th>Unit Price</th>
                <th>Amount</th>
                <th>Edit</th>
              </tr>
            </thead>
            <tbody>
              {importsProductsResponse?.map((IPD, index) => {
                return (
                  <tr key={index}>
                    <td>{index + 1}</td>
                    <td>{IPD.imports.uuid}</td>
                    <td>{IPD.product.name}</td>
                    <td>{IPD.importQuantity}</td>
                    <td>{IPD.unitPrice}</td>
                    <td>{IPD.amount}</td>
                    <td>
                      <Button
                        variant="warning"
                        onClick={() => onCheck(IPD.uuid)}
                      >
                        Edit
                      </Button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </Row>
      </Container>
    </div>
  );
};

export default UpdateImport;
