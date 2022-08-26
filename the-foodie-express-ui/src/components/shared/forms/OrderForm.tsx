import { Form, Formik, FormikConfig } from "formik";
import React from "react";
import { useNavigate } from "react-router-dom";
import { object, string } from "yup";
import { useCart } from "../../../cart/cart.hook";
import { OrderDto } from "../../../shared/generated";
import Button from "../../Base/Button";
import TextInput from "../../Base/TextInput";

const validator = object().shape({
  address: string().required("Внесете ја вашата адреса"),
});

interface OrderFormPropTypes {
  onOrder: (reg: OrderDto) => void;
}

const OrderForm: React.FC<OrderFormPropTypes> = ({ onOrder }) => {
  const navigate = useNavigate();
  const { items } = useCart();
  const total = items.reduce((prev, curr) => prev + (curr.dish?.price ?? 0) * (curr.quantity ?? 0), 0);
  const formik: FormikConfig<OrderDto> = {
    initialValues: {
      address: "",
      dateTime: "",
      price: total,
    },
    validationSchema: validator,
    onSubmit: (values) => {
      onOrder(values);
      navigate("/");
    },
  };

  return (
    <Formik {...formik}>
      <Form className="max-w-lg">
        <div>
          <TextInput label="Адреса за достава" type="text" name="address" id="address" />
        </div>
        <div className="mt-4">
          <TextInput label="Време за достава" type="datetime-local" name="dateTime" id="dateTime" />
        </div>
        <div className="my-4">
          <ul className="after:my-2 after:block after:h-0.5 after:w-full after:bg-orange-500">
            {items.map((it, idx) => (
              <li key={`${it.id}_${idx}`} className="flex justify-between text-lg font-medium">
                <span>{it.dish?.name}</span>
                <span>
                  {it.quantity} * {it.dish?.price?.toFixed(0)} ден.
                </span>
              </li>
            ))}
          </ul>
          <div className="flex justify-between text-2xl font-bold">
            <span>Вкупно:</span>
            <span>{total} ден.</span>
          </div>
        </div>
        <div className="text-right">
          <Button className="mt-4" type={"submit"}>
            Нарачај
          </Button>
        </div>
      </Form>
    </Formik>
  );
};

export default OrderForm;
